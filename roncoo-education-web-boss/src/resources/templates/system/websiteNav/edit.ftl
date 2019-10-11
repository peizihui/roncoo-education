<#assign base=request.contextPath /> 
<div class="bjui-pageContent">
	<form action="${base}/system/websiteNav/update" data-toggle="validate" method="post">
		<input type="hidden" name="id" value="${bean.id}"/>
		<div class="form-group">
            <label class="control-label x85">导航名称：</label>
            <input type="text" name="navName" value="${bean.navName!}" placeholder="导航名称" size="20">
        </div>
        <br />
        <div class="form-group">
            <label class="control-label x85">排序：</label>
            <input type="text" name="sort" value="${bean.sort!}" placeholder="排序" data-toggle="spinner" size="20">
        </div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">修改</button></li>
    </ul>
</div>