<%@ include file="../component/header.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<input type="hidden" id="requestCustomerId" value="${customerInfo.id}">

<div class="am-cf am-padding-left">
    <div class="am-fl am-cf">
        <strong class="am-text-primary am-text-lg">客户操作日志</strong>
        -
        <small>${customerInfo.customerName}</small>
    </div>
</div>
<div class="am-g am-margin-top">
    <div class="am-u-sm-12 am-u-md-5">
        <div class="am-btn-toolbar">
            <div class="am-btn-group am-btn-group-xs">
                <button type="button" class="am-btn am-btn-default" id="add-customer-log"><span
                        class="am-icon-plus"></span> 新增
                </button>
                <button type="button" class="am-btn am-btn-default"><span class="am-icon-trash-o"></span> 删除</button>
            </div>
        </div>
    </div>
    <div class="am-u-sm-12 am-u-md-4">
        <div class="am-form-group am-margin-bottom-0">
            <label class="am-form-label" for="customer-filter-select">客户：</label>
            <select data-am-selected="{btnSize: 'sm'}" id="customer-filter-select">
                <option value="-1">所有客户</option>
                <c:forEach var="customerItem" items="${customerList}">
                    <option value="<c:out value="${customerItem.id}"></c:out>"
                            <c:if test="${customerItem.id == requestCustomerId}">selected</c:if>><c:out
                            value="${customerItem.customerName}"></c:out></option>
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
        <table class="am-table am-table-striped am-table-hover table-main" id="customer-tracelog-table">
            <thead>
            <tr>
                <th class="table-check"><input type="checkbox"/></th>
                <th class="table-id">ID</th>
                <th class="table-title">销售人员</th>
                <th class="table-type">日志内容</th>
                <th class="table-author am-hide-sm-only">创建人</th>
                <th class="table-date am-hide-sm-only">创建时间</th>
                <th class="table-set">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="traceLog" items="${traceLogList}" begin="0">
                <tr>
                    <td><input type="checkbox"/></td>
                    <td><c:out value="${traceLog.id}"></c:out></td>
                    <td><c:out value="${traceLog.reportSalesman}"></c:out></td>
                    <td><c:choose>
                        <c:when test="${fn:length(traceLog.reportInfo) > 20}">
                            <c:out value="${fn:substring(traceLog.reportInfo,0,20)}..."></c:out>
                        </c:when>
                        <c:otherwise>
                            <c:out value="${traceLog.reportInfo}"></c:out>
                        </c:otherwise>
                    </c:choose></td>
                    <td class="am-hide-sm-only"><c:out value="${traceLog.creatorName}"></c:out></td>
                    <td class="am-hide-sm-only"><fmt:formatDate value="${traceLog.createTime}" type="both"
                                                                dateStyle="full"
                                                                pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
                    <td>
                        <div class="am-btn-toolbar" id="customer-item-btn-toolbar">
                            <div class="am-btn-group am-btn-group-xs">
                                <button class="am-btn am-btn-default am-btn-xs am-text-secondary am-btn-edit"
                                        item-id="<c:out value="${traceLog.id}"></c:out>"><span
                                        class="am-icon-pencil-square-o"></span> 编辑
                                </button>
                                <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only am-btn-delete"
                                        item-id="<c:out value="${traceLog.id}"></c:out>"><span
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
            共 <c:out value="${traceLogCount}"></c:out> 条记录
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
<script src="../../statics/js/customerTraceLog/list.js"></script>
<%@include file="../component/footer.jsp" %>