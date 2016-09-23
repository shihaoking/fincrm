<%@ include file="../component/header.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="am-cf am-padding-left">
    <div class="am-fl am-cf">
        <span class="am-icon-users am-text-primary"></span>
        <strong class="am-text-primary am-text-lg">客户管理</strong>
    </div>
</div>
<div class="am-g am-margin-top">
    <div class="am-u-sm-12 am-u-md-5">
        <div class="am-btn-toolbar">
            <div class="am-btn-group am-btn-group-xs">
                <button type="button" class="am-btn am-btn-default" id="add-customer"><span class="am-icon-plus"></span>
                    新增
                </button>
            </div>
        </div>
    </div>
    <sec:authorize access="hasRole('ROLE_MANAGER')">
        <div class="am-u-sm-12 am-u-md-4">
            <div class="am-form-group am-margin-bottom-0">
                <label class="am-form-label" for="salesman-select">业务员：</label>
                <select data-am-selected="{btnSize: 'sm'}" id="salesman-select">
                    <option value="-1">所有业务员</option>
                    <c:forEach var="salesmanInfo" items="${salesmanList}">
                        <option value="<c:out value="${salesmanInfo.id}"></c:out>"
                                <c:if test="${salesmanInfo.id == requestSalesmanId}">selected</c:if>><c:out value="${salesmanInfo.userName}"></c:out></option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </sec:authorize>
    <div class="am-u-sm-12 am-u-md-3">
        <div class="am-input-group am-input-group-sm">
            <input type="text" class="am-form-field" id="search-text" value="${param.get("name")}" placeholder="输入客户姓名">
            <span class="am-input-group-btn">
            <button class="am-btn am-btn-default" type="button" id="search-btn">搜索</button>
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
                                <button class="am-btn am-btn-default am-btn-xs am-text-secondary am-btn-edit"
                                        item-id="<c:out value="${customerInfo.id}"></c:out>"><span
                                        class="am-icon-pencil-square-o"></span> 编辑
                                </button>
                                <button class="am-btn am-btn-default am-btn-xs am-hide-sm-only"><a class="am-link-black"
                                                                                                   href="/customerTraceLog/list?id=<c:out value="${customerInfo.id}"></c:out>"><span
                                        class="am-icon-book"></span> 笔记</a>
                                </button>
                                <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only am-btn-delete"
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
        <%@include file="../component/pager.jsp" %>
    </div>
</div>
<!--弹窗窗口-->
<div class="am-modal am-modal-confirm" tabindex="-1" id="confirm-dialog">
    <div class="am-modal-dialog">
        <div class="am-modal-hd am-text-primary"></div>
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