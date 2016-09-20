<!--分页-->
<div class="am-cf">
    <c:set var="curtUrl" value="${fn:replace(pageContext.request.getRequestURL(), 'WEB-INF/pages/', '')}"></c:set>
    <c:set var="curtUrl" value="${fn:replace(curtUrl, '.jsp', '')}"></c:set>
    共 <c:out value="${pageInfo.total}"></c:out> 条记录
    <c:if test="${pageInfo.total != 0 && pageInfo.pages != 1}">
    <div class="am-fr">
        <c:set value="?" var="requestParams"></c:set>
        <c:forEach items="${param}" var="item">
            <c:if test="${item.key != 'pageNum'}">
                <c:set var="requestParams" value="${requestParams}${item.key}=${item.value}&"></c:set>
            </c:if>
        </c:forEach>
        <ul class="am-pagination am-margin-top-0 am-margin-bottom-0">
            <li
                    <c:if test="${param.containsKey('pageNum') && param.get('pageNum') ==  '1'}">
                        class="am-disabled"
                    </c:if>
            ><a href="<c:out value="${curtUrl}${requestParams}pageNum=1"></c:out>">首页</a></li>
            <c:forEach begin="1" end="${pageInfo.pages}" var="pageItem" step="1">
                <li
                        <c:if test="${param.containsKey('pageNum') && param.get('pageNum') ==  pageItem || param.containsKey('pageNum') == false && pageItem == 1}">
                            class="am-active"
                        </c:if>
                ><a href="<c:out value="${curtUrl}${requestParams}pageNum=${pageItem}"></c:out>"><c:out value="${pageItem}"></c:out></a></li>
            </c:forEach>
            <li
                    <c:if test="${param.containsKey('pageNum') && param.get('pageNum') ==  pageInfo.pages}">
                        class="am-disabled"
                    </c:if>
            ><a href="<c:out value="${curtUrl}${requestParams}pageNum=${pageInfo.pages}"></c:out>">末页</a></li>
        </ul>
    </div>
    </c:if>
</div>