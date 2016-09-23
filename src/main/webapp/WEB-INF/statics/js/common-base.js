/**
 * Created by jinshihao on 16/9/12.
 */
    //验证表单
var validFormInput = function (formId) {
        var passed = true;
        removeValidDialog();

        $(formId).find('[required]').each(function () {
            if ($(this).val() == '') {
                var $this = $(this);
                showValidFaildDialog($this, 'required');

                passed = false;
            }
        });

        $(formId).find('[pattern]').each(function () {
            var $this = $(this);
            if($this.val() == ''){
                return true;
            }

            var pattern = $this.attr('pattern');
            if(pattern == ''){
                return true;
            }

            var regexPattern = new RegExp(pattern);
            if (regexPattern.test($this.val()) == false) {

                showValidFaildDialog($this, 'pattern');

                passed = false;
            }
        });

        return passed;
    }

var bindAutoValidFormInput = function (formId) {
    $(formId).find('[required]').each(function () {
        $(this).on('focusin', function () {
            $(this).removeClass('am-field-error am-active').parent().parent().removeClass('am-form-error');
            $('.vld-tooltip[name="' + $(this).attr('id') + '"]').remove();
        }).on('focusout', function () {
            var $this = $(this);
        });

    });

    $(formId).find('[pattern]').each(function () {
        $(this).on('focusin', function () {
            $(this).removeClass('am-field-error am-active').parent().parent().removeClass('am-form-error');
            $('.vld-tooltip[name="' + $(this).attr('id') + '"]').remove();
        }).on('focusout', function () {
            var $this = $(this);
        });

    });
}

function showValidFaildDialog(target, type) {
    var offset = target.offset();

    target.parent().parent().addClass('am-form-error');
    target.addClass('am-field-error am-active');

    var msg = target.attr(type + '-valid-msg');

    var $tooltip = $('<div class="vld-tooltip" name="' + target.attr('id') + '"></div>');
    $tooltip.appendTo(document.body);

    $tooltip.text(msg).show().css({
        left: offset.left + 10,
        top: offset.top + target.outerHeight() + 10
    });
}

function removeValidDialog() {
    $('.vld-tooltip').remove();
}

function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}