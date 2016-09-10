<%@ include file="../component/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="am-g">
    <div class="am-u-sm-12 am-u-md-6">
        <div class="am-btn-toolbar">
            <div class="am-btn-group am-btn-group-xs">
                <button type="button" class="am-btn am-btn-default"><span class="am-icon-plus"></span> 新增</button>
                <button type="button" class="am-btn am-btn-default"><span class="am-icon-save"></span> 保存</button>
                <button type="button" class="am-btn am-btn-default"><span class="am-icon-archive"></span> 审核</button>
                <button type="button" class="am-btn am-btn-default"><span class="am-icon-trash-o"></span> 删除</button>
            </div>
        </div>
    </div>
    <div class="am-u-sm-12 am-u-md-3">
        <div class="am-form-group">
            <select data-am-selected="{btnSize: 'sm'}" id="salesman-select">
                <option value="-1">所有用户</option>
                <c:forEach var="salesmanInfo" items="${salesmanList}">
                    <option value="<c:out value="${salesmanInfo.id}"></c:out>" <c:if test="${salesmanInfo.id == requestSalesmanId}">selected</c:if>><c:out value="${salesmanInfo.userName}"></c:out></option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="am-u-sm-12 am-u-md-3">
        <div class="am-input-group am-input-group-sm">
            <input type="text" class="am-form-field">
            <span class="am-input-group-btn">
            <button class="am-btn am-btn-default" type="button">搜索</button>
          </span>
        </div>
    </div>
</div>
<div class="am-g">
    <div class="am-u-sm-12">
        <table class="am-table am-table-striped am-table-hover table-main" id="customer-info-table">
            <thead>
            <tr>
                <th class="table-check"><input type="checkbox"/></th>
                <th class="table-id">ID</th>
                <th class="table-title">姓名</th>
                <th class="table-type">手机</th>
                <th class="table-author am-hide-sm-only">邮箱</th>
                <th class="table-date am-hide-sm-only">创建时间</th>
                <th class="table-set">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="customerInfo" items="${customerList}" begin="0">
                <tr>
                    <td><input type="checkbox"/></td>
                    <td><c:out value="${customerInfo.id}"></c:out></td>
                    <td><c:out value="${customerInfo.customerName}"></c:out></td>
                    <td><c:out value="${customerInfo.phoneNumber}"></c:out></td>
                    <td class="am-hide-sm-only"><c:out value="${customerInfo.email}"></c:out></td>
                    <td class="am-hide-sm-only"><fmt:formatDate value="${customerInfo.createTime}" type="both"
                                                                dateStyle="full"
                                                                pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
                    <td>
                        <div class="am-btn-toolbar" id="customer-item-btn-toolbar">
                            <div class="am-btn-group am-btn-group-xs">
                                <button class="am-btn am-btn-default am-btn-xs am-text-secondary"
                                        item-id="<c:out value="${customerInfo.id}"></c:out>"><span
                                        class="am-icon-pencil-square-o"></span> 编辑
                                </button>
                                <button class="am-btn am-btn-default am-btn-xs am-hide-sm-only"><span
                                        class="am-icon-copy"></span> 复制
                                </button>
                                <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only"
                                        item-id="<c:out value="${customerInfo.id}"></c:out>"><span
                                        class="am-icon-trash-o"></span> 删除
                                </button>
                            </div>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="am-cf">
            共 <c:out value="${customerCount}"></c:out> 条记录
            <div class="am-fr">
                <ul class="am-pagination">
                    <li class="am-disabled"><a href="#">«</a></li>
                    <li class="am-active"><a href="#">1</a></li>
                    <li><a href="#">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#">5</a></li>
                    <li><a href="#">»</a></li>
                </ul>
            </div>
        </div>
        <hr/>
    </div>
</div>
<!--弹窗窗口-->
<button
        type="button"
        class="am-btn am-btn-warning"
        id="doc-confirm-toggle">
    Confirm
</button>
<div class="am-modal am-modal-confirm" tabindex="-1" id="confirm-dialog">
    <div class="am-modal-dialog">
        <div class="am-modal-hd"></div>
        <div class="am-modal-bd">
        </div>
        <div class="am-modal-footer">
            <span class="am-modal-btn" data-am-modal-cancel>取消</span>
            <span class="am-modal-btn" data-am-modal-confirm>确定</span>
        </div>
    </div>
</div>
<script src="../../statics/js/customer/list.js"></script>
<%@include file="../component/footer.jsp" %>