/**
 * Created by jinshihao on 16/9/10.
 */

$(function () {
    //客户信息编辑表单html模板
    var customerInfoEditForm = $('<form class="am-form am-form-horizontal" id="customer-info-edit-form"><fieldset>'
        + '<div class="am-form-group"><label for="doc-ipt-username" class="am-u-sm-3 am-form-label">姓名</label><div class="am-u-sm-9"><input type="text" id="doc-ipt-username" placeholder="姓名" required required-valid-msg="请填写客户姓名"></div></div>'
        + '<div class="am-form-group"><label for="doc-ipt-phone" class="am-u-sm-3 am-form-label">手机</label><div class="am-u-sm-9"><input type="text" id="doc-ipt-phone" placeholder="手机号" required required-valid-msg="请填写客户手机" pattern="^[0-9]{11}$" pattern-valid-msg="手机格式不正确"></div></div>'
        + '<div class="am-form-group"><label for="doc-ipt-email" class="am-u-sm-3 am-form-label">邮箱</label><div class="am-u-sm-9"><input type="text" id="doc-ipt-email" placeholder="邮箱" pattern="^[A-Za-z0-9_]+((\.|\-)?[A-Za-z0-9_]*)*@([A-Za-z0-9-]*\.){1,2}[A-Za-z]*$" pattern-valid-msg="邮箱格式不正确"></div></div>'
        + '<div class="am-form-group"><label for="doc-select-salesman" class="am-u-sm-3 am-form-label">业务员</label><div class="am-u-sm-9"><select id="doc-select-salesman" required required-valid-msg="请选择业务员"><option value="">请选择</option></select></div></div>'
        + '<div class="am-form-group"><label for="doc-select-status" class="am-u-sm-3 am-form-label">状态</label><div class="am-u-sm-9"><select id="doc-select-status"><option value="1">可用</option><option value="0">禁用</option></select></div></div>'
        + '</fieldset></form>');

    //填充编辑表单客户信息数据
    var fillCustomerInfo = function (id) {
        $.getJSON('/customer/getCustomerInfo', {'id': id}, function (result) {
            $('#customer-info-edit-form').find('#doc-ipt-username').val(result.customerName);
            $('#customer-info-edit-form').find('#doc-ipt-phone').val(result.phoneNumber);
            $('#customer-info-edit-form').find('#doc-ipt-email').val(result.email);
            $('#customer-info-edit-form').find('#doc-select-salesman').val(result.salesmanId);
            $('#customer-info-edit-form').find('#doc-select-status').val(result.status == true ? 1 : 0);
        });
    };

    //填充编辑表单销售员下拉框数据
    var fillSalesmanInfoToSelect = function (target) {
        if($('#loginUserRole').val() == 'ROLE_MANAGER') {
            $.getJSON('/salesman/getAllSalesman', {}, function (result) {
                $.each(result, function (index, val) {
                    target.append('<option value="' + val.id + '">' + val.userName + '</option>');
                });


            });
        }else{
            target.append('<option value="' + $('#loginUserId').val() + '">' + $('#loginUserName').val() + '</option>');
        }
    };
    var salesmanSelect = customerInfoEditForm.find('#doc-select-salesman');
    fillSalesmanInfoToSelect(salesmanSelect);

    //更新客户信息
    var updateCustomerInfo = function (id, name, phone, email, salesmanId, status) {
        var result = new Object();
        $.ajax({
            url: '/customer/update',
            data: JSON.stringify({
                customerId: id,
                customerName: name,
                phoneNumber: phone,
                email: email,
                salesmanId: salesmanId,
                status: (status == 1 ? true : false),
            }),
            dateType: 'json',
            async: false,
            type: 'POST',
            contentType: 'application/json;charset=UTF-8',
            success: function (result) {
                if (result.success) {
                    result = {success: true};
                } else {
                    result = {success: false, msg: result.errorMsg};
                }
            }

            ,
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                result = {success: false, msg: XMLHttpRequest.status + ',请联系管理员'};
            }
        })
        ;

        return result;
    };

    //更新客户信息
    var addCustomerInfo = function (id, name, phone, email, salesmanId, status) {
        var resultData = new Object();
        $.ajax({
            url: '/customer/add',
            data: JSON.stringify({
                customerId: id,
                customerName: name,
                phoneNumber: phone,
                email: email,
                salesmanId: salesmanId,
                status: (status == 1 ? true : false),
            }),
            dateType: 'json',
            async: false,
            type: 'POST',
            contentType: 'application/json;charset=UTF-8',
            success: function (result) {
                if (result.success) {
                    resultData = {success: true};
                } else {
                    resultData = {success: false, msg: result.errorMsg};
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                resultData = {success: false, msg: XMLHttpRequest.status + ',请联系管理员'};
            }
        })
        ;

        return resultData;
    };

    var deleteCustomerInfo = function (id) {
        var resultData = new Object();
        $.ajax({
            url: '/customer/delete',
            data: {id: id},
            dateType: 'json',
            async: false,
            type: 'POST',
            success: function (result) {
                if (result.success) {
                    resultData = {success: true};
                } else {
                    resultData = {success: false, msg: result.errorMsg};
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                resultData = {success: false, msg: XMLHttpRequest.status + ',请联系管理员'};
            }
        })
        ;

        return resultData;
    };

    //客户信息表格编辑按钮事件绑定
    $('#customer-info-table').find('.am-btn-edit').on('click', function () {
        removeValidDialog();
        var relatedTarget = this;
        $('#confirm-dialog').find('.am-modal-hd').text('修改客户信息');
        $('#confirm-dialog').find('.am-modal-bd').empty();
        $('#confirm-dialog').find('.am-modal-bd').append(customerInfoEditForm.clone());

        fillCustomerInfo($(relatedTarget).attr('item-id'));

        $('#confirm-dialog').modal();

        bindAutoValidFormInput('#customer-info-edit-form');

        var $confirm = $('#confirm-dialog');
        var $confirmBtn = $confirm.find('[data-am-modal-confirm]');
        var $cancelBtn = $confirm.find('[data-am-modal-cancel]');

        $confirmBtn.off('click.confirm.modal.amui').off('click').on('click', function () {
            if (validFormInput('#customer-info-edit-form') == false) {
                return false;
            }

            var customerInfoForm = $('#customer-info-edit-form');
            var customerId = $(relatedTarget).attr('item-id');
            var customerName = customerInfoForm.find('#doc-ipt-username').val();
            var customerPhone = customerInfoForm.find('#doc-ipt-phone').val();
            var customerEmail = customerInfoForm.find('#doc-ipt-email').val();
            var customerOfSalesmanId = customerInfoForm.find('#doc-select-salesman').val();
            var customerStatus = customerInfoForm.find('#doc-select-status').val();

            var result = updateCustomerInfo(customerId, customerName, customerPhone, customerEmail, customerOfSalesmanId, customerStatus);
            if (result.success == false) {
                alert(result.msg);
                return false;
            }

            window.location.reload();
        });

        $cancelBtn.off('click.cancel.modal.amui').off('click').on('click', function () {
            removeValidDialog();
            return true;
        });
    });

    //顶部销售员过滤列表切换业务员事件绑定
    $('#salesman-select').change(function () {
        window.location.href = '/customer/list?id=' + $(this).val();
    });

    //顶部新建按钮
    $('#add-customer').click(function () {
        removeValidDialog();
        var relatedTarget = this;
        $('#confirm-dialog').find('.am-modal-hd').text('添加客户信息');
        $('#confirm-dialog').find('.am-modal-bd').empty();
        $('#confirm-dialog').find('.am-modal-bd').append(customerInfoEditForm.clone());
        $('#confirm-dialog').modal();

        $('#customer-info-edit-form').find('#doc-select-salesman').val($('#loginUserId').val());
        bindAutoValidFormInput('#customer-info-edit-form');

        var $confirm = $('#confirm-dialog');
        var $confirmBtn = $confirm.find('[data-am-modal-confirm]');
        var $cancelBtn = $confirm.find('[data-am-modal-cancel]');

        $confirmBtn.off('click.confirm.modal.amui').off('click').on('click', function () {
            if (validFormInput('#customer-info-edit-form') == false) {
                return false;
            }

            var customerInfoForm = $('#customer-info-edit-form');
            var customerId = $(relatedTarget).attr('item-id');
            var customerName = customerInfoForm.find('#doc-ipt-username').val();
            var customerPhone = customerInfoForm.find('#doc-ipt-phone').val();
            var customerEmail = customerInfoForm.find('#doc-ipt-email').val();
            var customerOfSalesmanId = customerInfoForm.find('#doc-select-salesman').val();
            var customerStatus = customerInfoForm.find('#doc-select-status').val();

            var result = addCustomerInfo(customerId, customerName, customerPhone, customerEmail, customerOfSalesmanId, customerStatus);
            if (result.success == false) {
                alert(result.msg);
                return false;
            }

            window.location.reload();
        });

        $cancelBtn.off('click.cancel.modal.amui').off('click').on('click', function () {

            removeValidDialog();
            return true;
        });
    });

    $('#customer-info-table').find('.am-btn-delete').on('click', function () {
        var relatedTarget = this;
        $('#confirm-dialog').find('.am-modal-hd').text('');
        $('#confirm-dialog').find('.am-modal-bd').html('确定要删除这条记录吗？');

        $('#confirm-dialog').modal({
            relatedTarget: this,
            onConfirm: function (options) {
                var customerId = $(relatedTarget).attr('item-id');
                var result = deleteCustomerInfo(customerId);

                if (result.success) {
                    window.location.reload();
                } else {
                    alert(result.msg)
                    false;
                }
            },
            onCancel: function () {

            }
        });
    });

    $('#search-btn').click(function () {
        var searchTxt = $('#search-text').val();
        var idRequestVal = getUrlParam('id');

        if(searchTxt == ''){
            if(idRequestVal == null){
                window.location.href = '/customer/list';
            }else{
                window.location.href = '/customer/list?id=' + idRequestVal;
            }
        }

        if(idRequestVal == null){
            window.location.href = '/customer/list?name=' + searchTxt;
        }else{
            window.location.href = '/customer/list?id=' + idRequestVal + '&name=' + searchTxt;
        }
    });
});
