/**
 * Created by jinshihao on 16/8/25.
 */
$(document).ready(function () {
    var customer_count_target = $('.customer_count');

    var item_ids = [];
    $.each(customer_count_target, function (index, val) {
        item_ids.push($(val).attr('item-id'));
    });

    $.getJSON('/customer/getCountBySalesmanIds', {'ids': item_ids.join(',')}, function (result) {
        $.each(result, function (index, val) {
            $('#customer_count_' + val.salesmanId).html(val.customerCount);
        })
    });

    //客户信息编辑表单html模板
    var salesmanInfoEditForm = $('<form class="am-form am-form-horizontal" id="salesman-info-edit-form"><fieldset>'
            + '<div class="am-form-group"><label for="doc-ipt-username" class="am-u-sm-3 am-form-label">姓名</label><div class="am-u-sm-9"><input type="text" id="doc-ipt-username" placeholder="姓名" required required-valid-msg="请填写客户姓名"></div></div>'
            + '<div class="am-form-group"><label for="doc-ipt-phone" class="am-u-sm-3 am-form-label">手机</label><div class="am-u-sm-9"><input type="text" id="doc-ipt-phone" placeholder="手机号" required required-valid-msg="请填写客户手机" pattern="^[0-9]{11}$" pattern-valid-msg="手机格式不正确"></div></div>'
            + '<div class="am-form-group"><label for="doc-ipt-email" class="am-u-sm-3 am-form-label">邮箱</label><div class="am-u-sm-9"><input type="text" id="doc-ipt-email" placeholder="邮箱" pattern="^[A-Za-z0-9_]+((\.|\-)?[A-Za-z0-9_]*)*@([A-Za-z0-9-]*\.){1,2}[A-Za-z]*$" pattern-valid-msg="邮箱格式不正确"></div></div>'
            + '<div class="am-form-group"><label for="doc-ipt-gender" class="am-u-sm-3 am-form-label">性别</label><div class="am-u-sm-9"><select id="doc-select-gender"><option value="1">男</option><option value="0">女</option></select></div></div>'
            + '<div class="am-form-group"><label for="doc-ipt-pwd" class="am-u-sm-3 am-form-label">密码</label><div class="am-u-sm-9"><input type="password" id="doc-ipt-pwd" placeholder="密码" required required-valid-msg="请填写登录密码" maxlength="16"></div></div>'
            + '<div class="am-form-group"><label for="doc-select-manager" class="am-u-sm-3 am-form-label">经理</label><div class="am-u-sm-9"><select id="doc-select-manager" required required-valid-msg="请选择经理"><option value="">请选择</option></select></div></div>'
            + '<div class="am-form-group"><label for="doc-select-status" class="am-u-sm-3 am-form-label">状态</label><div class="am-u-sm-9"><select id="doc-select-status"><option value="1">可用</option><option value="0">禁用</option></select></div></div>'
            + '</fieldset></form>');

    //填充编辑表单销售员下拉框数据
    var fillManagerInfoToSelect = function (target) {
        $.getJSON('/salesman/getAllManager', {}, function (result) {
            $.each(result, function (index, val) {
                target.append('<option value="' + val.id + '">' + val.userName + '</option>');
            })
        });
    };
    var managerSelect = salesmanInfoEditForm.find('#doc-select-manager');
    fillManagerInfoToSelect(managerSelect);

    //填充编辑表单客户信息数据
    var fillSalesmanInfo = function (id) {
        $.getJSON('/salesman/getSalesmanInfo', {'id': id}, function (result) {
            $('#salesman-info-edit-form').find('#doc-ipt-username').val(result.userName);
            $('#salesman-info-edit-form').find('#doc-ipt-phone').val(result.phonenumber);
            $('#salesman-info-edit-form').find('#doc-ipt-email').val(result.email);
            $('#salesman-info-edit-form').find('#doc-ipt-email').val(result.email);
            $('#salesman-info-edit-form').find('#doc-ipt-pwd').val(result.userPwd);
            $('#salesman-info-edit-form').find('#doc-select-manager').val(result.managerId);
            $('#salesman-info-edit-form').find('#doc-select-gender').val(result.gender == true ? 1 : 0);
            $('#salesman-info-edit-form').find('#doc-select-status').val(result.status == true ? 1 : 0);
        });
    };

    //更新客户信息
    var updateSalesmanInfo = function (id, name, phone, email, gender, pwd, managerId, status) {
        var result = new Object();
        $.ajax({
            url: '/salesman/update',
            data: JSON.stringify({
                salesmanId: id,
                userName: name,
                phonenumber: phone,
                gender: (status == 1 ? true : false),
                email: email,
                userPwd: pwd,
                managerId: managerId,
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
    var addSalesmanInfo = function (id, name, phone, email, gender, pwd, managerId, status) {
        var resultData = new Object();
        $.ajax({
            url: '/salesman/add',
            data: JSON.stringify({
                salesmanId: id,
                userName: name,
                phonenumber: phone,
                email: email,
                gender: (status == 1 ? true : false),
                userPwd: pwd,
                managerId: managerId,
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

    var deleteSalesmanInfo = function (id) {
        var resultData = new Object();
        $.ajax({
            url: '/salesman/delete',
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
    $('#salesman-info-table').find('.am-btn-edit').on('click', function () {
        removeValidDialog();
        var relatedTarget = this;
        $('#confirm-dialog').find('.am-modal-hd').text('修改业务员信息');
        $('#confirm-dialog').find('.am-modal-bd').empty();
        $('#confirm-dialog').find('.am-modal-bd').append(salesmanInfoEditForm.clone());

        fillSalesmanInfo($(relatedTarget).attr('item-id'));

        $('#confirm-dialog').modal();

        bindAutoValidFormInput('#salesman-info-edit-form');

        var $confirm = $('#confirm-dialog');
        var $confirmBtn = $confirm.find('[data-am-modal-confirm]');
        var $cancelBtn = $confirm.find('[data-am-modal-cancel]');

        $confirmBtn.off('click.confirm.modal.amui').off('click').on('click', function () {
            if (validFormInput('#salesman-info-edit-form') == false) {
                return false;
            }

            var salesmanInfoForm = $('#salesman-info-edit-form');
            var salesmanId = $(relatedTarget).attr('item-id');
            var salesmanName = salesmanInfoForm.find('#doc-ipt-username').val();
            var salesmanPhone = salesmanInfoForm.find('#doc-ipt-phone').val();
            var salesmanEmail = salesmanInfoForm.find('#doc-ipt-email').val();
            var salesmanPwd = salesmanInfoForm.find('#doc-ipt-pwd').val();
            var managerId = salesmanInfoForm.find('#doc-select-manager').val();
            var gender = salesmanInfoForm.find('#doc-select-gender').val();
            var salesmanStatus = salesmanInfoForm.find('#doc-select-status').val();

            var result = updateSalesmanInfo(salesmanId, salesmanName, salesmanPhone, salesmanEmail, gender, salesmanPwd, managerId, salesmanStatus);
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

    //顶部新建按钮
    $('#add-salesman').click(function () {
        removeValidDialog();
        var relatedTarget = this;
        $('#confirm-dialog').find('.am-modal-hd').text('添加业务员信息');
        $('#confirm-dialog').find('.am-modal-bd').empty();
        $('#confirm-dialog').find('.am-modal-bd').append(salesmanInfoEditForm.clone());
        $('#confirm-dialog').modal();

        bindAutoValidFormInput('#salesman-info-edit-form');

        var $confirm = $('#confirm-dialog');
        var $confirmBtn = $confirm.find('[data-am-modal-confirm]');
        var $cancelBtn = $confirm.find('[data-am-modal-cancel]');

        $confirmBtn.off('click.confirm.modal.amui').off('click').on('click', function () {
            if (validFormInput('#salesman-info-edit-form') == false) {
                return false;
            }

            var salesmanInfoForm = $('#salesman-info-edit-form');
            var salesmanId = $(relatedTarget).attr('item-id');
            var salesmanName = salesmanInfoForm.find('#doc-ipt-username').val();
            var salesmanPhone = salesmanInfoForm.find('#doc-ipt-phone').val();
            var salesmanEmail = salesmanInfoForm.find('#doc-ipt-email').val();
            var salesmanPwd = salesmanInfoForm.find('#doc-ipt-pwd').val();
            var managerId = salesmanInfoForm.find('#doc-select-manager').val();
            var gender = salesmanInfoForm.find('#doc-select-gender').val();
            var salesmanStatus = salesmanInfoForm.find('#doc-select-status').val();

            var result = addSalesmanInfo(salesmanId, salesmanName, salesmanPhone, salesmanEmail, gender, salesmanPwd, managerId, salesmanStatus);
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

    $('#salesman-info-table').find('.am-btn-delete').on('click', function () {
        var relatedTarget = this;
        $('#confirm-dialog').find('.am-modal-hd').text('');
        $('#confirm-dialog').find('.am-modal-bd').html('确定要删除这条记录吗？');

        $('#confirm-dialog').modal({
            relatedTarget: this,
            onConfirm: function (options) {
                var salesmanId = $(relatedTarget).attr('item-id');
                var result = deleteSalesmanInfo(salesmanId);

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

        if(searchTxt == '') {
            window.location.href = '/salesman/list';
        }

        window.location.href = '/salesman/list?name=' + searchTxt;
    });
});