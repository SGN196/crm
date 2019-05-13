<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>商机跟进管理</title>
	<meta name="decorator" content="ani"/>
	<script type="text/javascript">

		$(document).ready(function() {
            $("textarea").css("resize", "none");

            $('#date').datetimepicker({
                format: "YYYY-MM-DD HH:mm"
            });
		});
		function save() {
            var isValidate = jp.validateForm('#inputForm');//校验表单
            if(!isValidate){
                return false;
			}else{
                jp.loading();
                jp.post("${ctx}/oppactivities/oppActivities/save",$('#inputForm').serialize(),function(data){
                    if(data.success){
                        jp.getParent().refresh();
                        var dialogIndex = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                        parent.layer.close(dialogIndex);
                        jp.success(data.msg)

                    }else{
                        jp.error(data.msg);
                    }
                })
			}

        }
	</script>
</head>
<body class="bg-white">
		<form:form id="inputForm" modelAttribute="oppActivities" class="form-horizontal">
		<form:hidden path="id"/>	
		<table class="table table-bordered">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>商机编号：</label></td>
					<td class="width-35">
						<form:hidden path="oppId" htmlEscape="false"    class="form-control required"/>
						<input type="text" id="s" value="${opportunity.oppName}" class="form-control required" readonly>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>活动类型：</label></td>
					<td class="width-35">
						<form:select path="actTypeID" class="form-control required">
							<form:option value="" label=""/>
							<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">排序序号：</label></td>
					<td class="width-35">
						<form:input path="order" htmlEscape="false"    class="form-control  isIntGteZero"/>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>联系人编号：</label></td>
					<td class="width-35">
						<form:select path="contactID" class="form-control required">
							<form:option value="" label=""/>
							<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>跟进时间：</label></td>
					<td class="width-35">
						<%--<form:input path="date" htmlEscape="false"    class="form-control required"/>--%>
						<div class='input-group form_datetime' id='date'>
							<input type='text' name="date" class="form-control "  value=""/>
							<span class="input-group-addon">
								<span class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</td>
					<td class="width-15 active"></td>
					<td class="width-35"></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">跟进内容：</label></td>
					<td colspan="3">
						<form:textarea path="detail" htmlEscape="false" rows="4"    class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">跟进人编号：</label></td>
					<td class="width-35">
						<form:input path="empID" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">记录时间：</label></td>
					<td class="width-35">
						<form:input path="billDate" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">备注信息：</label></td>
					<td colspan="3">
						<form:textarea path="remarks" htmlEscape="false" rows="4"    class="form-control "/>
					</td>
		  		</tr>
		 	</tbody>
		</table>
	</form:form>
</body>
</html>