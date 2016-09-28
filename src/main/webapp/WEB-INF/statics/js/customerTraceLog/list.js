/**
 * Created by jinshihao on 16/9/18.
 */
$(function () {
    //客户信息编辑表单html模板
    var customerInfoEditForm = $('<form class="am-form am-form-horizontal" id="customer-tracelog-edit-form"><fieldset>'
        + '<div class="am-form-group"><label for="doc-select-customer" class="am-u-sm-3 am-form-label">客户</label><div class="am-u-sm-9"><select id="doc-select-customer" required required-valid-msg="请选择客户"><option value="">请选择</option></select></div></div>'
        + '<div class="am-form-group"><label for="doc-ipt-log" class="am-u-sm-3 am-form-label">日志</label><div class="am-u-sm-9"><textarea rows="8" id="doc-ipt-log" required required-valid-msg="请填写内容" /></div></div>'
        + '<div class="am-form-group"><label for="doc-select-salesman" class="am-u-sm-3 am-form-label">业务员</label><div class="am-u-sm-9"><select id="doc-select-salesman" required required-valid-msg="请选择业务员"><option value="">请选择</option></select></div></div>'
        + '<div class="am-form-group"><label for="doc-select-status" class="am-u-sm-3 am-form-label">状态</label><div class="am-u-sm-9"><select id="doc-select-status"><option value="1">可用</option><option value="0">禁用</option></select></div></div>'
        + '</fieldset></form>');

    //填充编辑表单客户信息数据
    var fillCustomerTraceLog = function (id) {
        $.getJSON('/customerTraceLog/get', {'id': id}, function (result) {
            $('#customer-tracelog-edit-form').find('#doc-ipt-log').val(result.reportInfo);
            $('#customer-tracelog-edit-form').find('#doc-select-customer').val(result.customerId);
            $('#customer-tracelog-edit-form').find('#doc-select-salesman').val(result.reportSalesmanId);
            $('#customer-tracelog-edit-form').find('#doc-select-status').val(result.status == true ? 1 : 0);
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

    //填充编辑表单客户下拉框数据
    var fillCustomerInfoToSelect = function (target) {
        $.getJSON('/customer/getBySalesmanId', {id: $('#loginUserId').val()}, function (result) {
            $.each(result, function (index, val) {
                target.append('<option value="' + val.id + '">' + val.customerName + '</option>');
            });
        });
    };

    fillSalesmanInfoToSelect(customerInfoEditForm.find('#doc-select-salesman'));
    fillCustomerInfoToSelect(customerInfoEditForm.find('#doc-select-customer'));

    //顶部销售员过滤列表切换业务员事件绑定
    $('#customer-filter-select').change(function () {
        window.location.href = '/customerTraceLog/list?id=' + $(this).val();
    });

    //更新日志信息
    var updateCustomerInfo = function (id, logInfo, customerId, salesmanId, status) {
        var result = new Object();
        $.ajax({
            url: '/customerTraceLog/update',
            data: JSON.stringify({
                id: id,
                reportInfo: logInfo,
                customerId: customerId,
                reportSalesmanId: salesmanId,
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

    //添加日志信息
    var addCustomerInfo = function (logInfo, customerId, salesmanId, status) {
        var resultData = new Object();
        $.ajax({
            url: '/customerTraceLog/add',
            data: JSON.stringify({
                reportInfo: logInfo,
                customerId: customerId,
                reportSalesmanId: salesmanId,
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
            url: '/customerTraceLog/delete',
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
    $('#customer-tracelog-table').find('.am-btn-edit').on('click', function () {
        removeValidDialog();
        var relatedTarget = this;
        $('#confirm-dialog').find('.am-modal-hd').text('修改笔记');
        $('#confirm-dialog').find('.am-modal-bd').empty();
        $('#confirm-dialog').find('.am-modal-bd').append(customerInfoEditForm.clone());

        fillCustomerTraceLog($(relatedTarget).attr('item-id'));

        $('#confirm-dialog').modal();

        bindAutoValidFormInput('#customer-tracelog-edit-form');

        var $confirm = $('#confirm-dialog');
        var $confirmBtn = $confirm.find('[data-am-modal-confirm]');
        var $cancelBtn = $confirm.find('[data-am-modal-cancel]');

        $confirmBtn.off('click.confirm.modal.amui').off('click').on('click', function () {
            if (validFormInput('#customer-tracelog-edit-form') == false) {
                return false;
            }

            var customerInfoForm = $('#customer-tracelog-edit-form');
            var id = $(relatedTarget).attr('item-id');
            var reportLog = customerInfoForm.find('#doc-ipt-log').val();
            var customerId = customerInfoForm.find('#doc-select-customer').val();
            var salesmanId = customerInfoForm.find('#doc-select-salesman').val();
            var status = customerInfoForm.find('#doc-select-status').val();

            var result = updateCustomerInfo(id, reportLog, customerId, salesmanId, status);
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
    $('#add-customer-log').click(function () {
        removeValidDialog();

        $('#confirm-dialog').find('.am-modal-hd').text('添加笔记');
        $('#confirm-dialog').find('.am-modal-bd').empty();
        $('#confirm-dialog').find('.am-modal-bd').append(customerInfoEditForm.clone());
        $('#confirm-dialog').modal();

        $('#customer-tracelog-edit-form').find('#doc-select-customer').val($('#requestCustomerId').val());
        $('#customer-tracelog-edit-form').find('#doc-select-salesman').val($('#loginUserId').val());

        bindAutoValidFormInput('#customer-tracelog-edit-form');

        var $confirm = $('#confirm-dialog');
        var $confirmBtn = $confirm.find('[data-am-modal-confirm]');
        var $cancelBtn = $confirm.find('[data-am-modal-cancel]');

        $confirmBtn.off('click.confirm.modal.amui').off('click').on('click', function () {
            if (validFormInput('#customer-tracelog-edit-form') == false) {
                return false;
            }

            var customerInfoForm = $('#customer-tracelog-edit-form');
            var reportLog = customerInfoForm.find('#doc-ipt-log').val();
            var customerId = customerInfoForm.find('#doc-select-customer').val();
            var salesmanId = customerInfoForm.find('#doc-select-salesman').val();
            var status = customerInfoForm.find('#doc-select-status').val();

            var result = addCustomerInfo(reportLog, customerId, salesmanId, status);
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

    $('#customer-tracelog-table').find('.am-btn-delete').on('click', function () {
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

    })
});