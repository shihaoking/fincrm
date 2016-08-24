<%@include file="../component/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="am-g">
    <div class="am-u-sm-12 am-u-md-6">
        <div class="am-btn-toolbar">
            <div class="am-btn-group am-btn-group-xs">
                <button type="button" class="am-btn am-btn-default"><span class="am-icon-plus"></span> 新增</button>
                <button type="button" class="am-btn am-btn-default"><span class="am-icon-save"></span> 保存</button>
                <button type="button" class="am-btn am-btn-default"><span class="am-icon-trash-o"></span> 删除</button>
            </div>
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
        <form class="am-form">
            <table class="am-table am-table-striped am-table-hover table-main">
                <thead>
                <tr>
                    <th class="table-check"><input type="checkbox"/></th>
                    <th class="table-id">ID</th>
                    <th class="table-title">姓名</th>
                    <th class="table-type">客户数</th>
                    <th class="table-phome">电话</th>
                    <th class="table-email">邮箱</th>
                    <th class="table-set">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="userInfo" items="${salesmanList}" begin="0">
                    <tr>
                        <td><input type="checkbox"/></td>
                        <td><c:out value="${userInfo.id}"></c:out></td>
                        <td><a href="/customer/list?id=<c:out value="${userInfo.id}"></c:out>"><c:out
                                value="${userInfo.userName}"></c:out></a></td>
                        <td>56</td>
                        <td><c:out value="${userInfo.phonenumber}"></c:out></td>
                        <td><c:out value="${userInfo.email}"></c:out></td>
                        <td>
                            <div class="am-btn-toolbar">
                                <div class="am-btn-group am-btn-group-xs">
                                    <button class="am-btn am-btn-default am-btn-xs am-text-secondary"
                                            item-id="<c:out value="${userInfo.id}"></c:out>"><span
                                            class="am-icon-pencil-square-o"></span> 编辑
                                    </button>
                                    <button class="am-btn am-btn-default am-btn-xs am-hide-sm-only"><a
                                            href="/customer/list?id=<c:out value="${userInfo.id}"></c:out>"><span
                                            class="am-icon-user"></span> 客户</a>
                                    </button>
                                    <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only"
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
        </form>
    </div>
</div>
<%@include file="../component/footer.jsp" %>