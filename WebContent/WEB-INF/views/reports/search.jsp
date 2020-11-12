<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>日報　検索結果</h2>
            <form method="get" action="<c:url value='/reports/search' />">
                <p>検索ワード: <input type="text" name="result"></p>
                    <c:if test="${err == 1}">
                        <div id="search_error">
                        社員番号を検索する場合は数字で入力してください。<br />
                        </div>
                    </c:if>
                    <c:if test="${err == 2}">
                        <div id="search_error">
                        検索条件を指定してください。<br />
                        </div>
                    </c:if>
                <p>検索条件:
                    <input type="radio" name="columnName" value="employee_id"> 社員番号
                    <input type="radio" name="columnName" value="name"> 氏名
                    <input type="radio" name="columnName" value="title"> タイトル
                    <input type="radio" name="columnName" value="content"> 内容
                </p>
                <input type="submit" value="検索">
            </form>
        <table id="report_list">
            <tbody>
                <tr>
                    <th class="report_employee_id">社員番号</th>
                    <th class="report_name">氏名</th>
                    <th class="report_date">日付</th>
                    <th class="report_title">タイトル</th>
                    <th class="report_action">操作</th>
                </tr>
                <c:forEach var="report" items="${reports}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="report_employee_id"><c:out value="${report.employee.id}" /></td>
                        <td class="report_name"><c:out value="${report.employee.name}" /></td>
                        <td class="report_date"><fmt:formatDate value='${report.report_date}' pattern='yyyy-MM-dd' /></td>
                        <td class="report_title">${report.title}</td>
                        <td class="report_action"><a href="<c:url value='/reports/show?id=${report.id}' />">詳細を見る</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${reports_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((reports_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/reports/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p><a href="<c:url value='/reports/new' />">新規日報の登録</a></p>

    </c:param>
</c:import>