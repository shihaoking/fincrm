<%@ include file="../component/header.jsp" %>

<div class="am-cf am-padding-left">
    <div class="am-fl am-cf">
        <span class="am-icon-black-tie am-text-primary"></span>
        <strong class="am-text-primary am-text-lg">经理管理</strong>
    </div>
</div>
<div class="am-g am-margin-top">
    <div class="am-u-sm-12 am-u-md-6">
        <div class="am-btn-toolbar">
            <div class="am-btn-group am-btn-group-xs">
                <button type="button" class="am-btn am-btn-default" id="add-salesmanager"><span class="am-icon-plus"></span> 新增</button>
            </div>
        </div>
    </div>
    <div class="am-u-sm-12 am-u-md-3">
        <div class="am-input-group am-input-group-sm">
            <input type="text" class="am-form-field" id="search-text" value="${param.get("name")}" placeholder="输入经理姓名">
            <span class="am-input-group-btn">
            <button class="am-btn am-btn-default" type="button" id="search-btn">搜索</button>
          </span>
        </div>
    </div>
</div>
<div class="am-g">
    <div class="am-u-sm-12">
        <table class="am-table am-table-striped am-table-hover table-main" id="salesmanager-info-table">
            <thead>
            <tr>
                <th class="table-check"><input type="checkbox"/></th>
                <th class="table-id">ID</th>
                <th class="table-title">姓名</th>
                <th class="table-phome">电话</th>
                <th class="table-email">邮箱</th>
                <th class="table-set">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="userInfo" items="${managerList}" begin="0">
                <tr>
                    <td><input type="checkbox"/></td>
                    <td><c:out value="${userInfo.id}"></c:out></td>
                    <td><a href="/customer/list?id=<c:out value="${userInfo.id}"></c:out>"><c:out
                            value="${userInfo.userName}"></c:out></a></td>
                    <td><c:out value="${userInfo.phonenumber}"></c:out></td>
                    <td><c:out value="${userInfo.email}"></c:out></td>
                    <td>
                        <div class="am-btn-toolbar">
                            <div class="am-btn-group am-btn-group-xs">
                                <button class="am-btn am-btn-default am-btn-xs am-text-secondary am-btn-edit"
                                        item-id="<c:out value="${userInfo.id}"></c:out>"><span
                                        class="am-icon-pencil-square-o"></span> 编辑
                                </button>
                                <button class="am-btn am-btn-default am-btn-xs am-hide-sm-only"><a class="am-link-black"
                                                                                                   href="/salesman/list?id=<c:out value="${userInfo.id}"></c:out>"><span
                                        class="am-icon-user-secret"></span> 业务员</a>
                                </button>
                                <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only am-btn-delete"
                                        item-id="<c:out value="${userInfo.id}"></c:out>"><span
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
<button
        type="button"
        class="am-btn am-btn-warning"
        id="doc-confirm-toggle" style="display: none">
    Confirm
</button>
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
<script src="../../statics/js/salesmanager/list.js"></script>
<%@include file="../component/footer.jsp" %>
