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
});