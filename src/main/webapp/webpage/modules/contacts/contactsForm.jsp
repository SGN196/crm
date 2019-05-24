<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>客户联系人管理</title>
	<meta name="decorator" content="ani"/>
	<script type="text/javascript">

		$(document).ready(function() {
			if (${id == null || id == ''}) {
				if ($("input[name='gender']").val() == '1') {
					$("input[name='gender']").attr("checked", "checked");
					// alert($("input[name='gender']").val());
				}
			}
		});
		function save() {
            var isValidate = jp.validateForm('#inputForm');//校验表单
            if(!isValidate){
                return false;
			}else{
                jp.loading();
                jp.post("${ctx}/contacts/contacts/save",$('#inputForm').serialize(),function(data){
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
		<form:form id="inputForm" modelAttribute="contacts" class="form-horizontal">
		<form:hidden path="id"/>	
		<table class="table table-bordered">
		   <tbody>
				<tr>
					<%--<td class="width-15 active"><label class="pull-right"><font color="red">*</font>所属业务员：</label></td>
					<td class="width-35">
						<form:input path="name" htmlEscape="false"    class="form-control required"/>
					</td>--%>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>姓名：</label></td>
					<td class="width-35">
						<form:input path="name" htmlEscape="false"    class="form-control required"/>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>性别：</label></td>
					<td class="width-35">
						<form:radiobuttons path="gender" items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks required"/>
					</td>
				</tr>
				<tr>

					<td class="width-15 active"><label class="pull-right">所属客户：</label></td><%--<font color="red">*</font>--%>
					<td class="width-35">
							<marketing:customerSelect id="tcusId" name="customer.id" value="${contacts.customer.id}" labelName="customer.name" labelValue="${contacts.customer.name}"
                                                      fieldLabels="客户名称|客户法人|跟进状态" fieldKeys="name|legalPerson|statusId" searchLabels="客户名称|客户法人|跟进状态"
                                                      searchKeys="name|legalPerson|statusId" title="选择所属公司" url="${ctx}/customer/customer/data" cssClass="form-control "/>
						<%--<marketing:customerSelect id="tcusId" name="cusId" value="${contacts.cusId}" labelName="customer.id" labelValue="${contacts.cusId}"
												  fieldLabels="客户名称|客户法人|跟进状态" fieldKeys="name|legalPerson|statusId" searchLabels="客户名称|客户法人|跟进状态" searchKeys="name|legalPerson|statusId" title="选择所属公司" url="${ctx}/customer/customer/data" cssClass="form-control "/>--%>

					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>身份证号码：</label></td>
					<td class="width-35">
						<form:input path="cardId" htmlEscape="false" class="form-control required"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>状态：</label></td>
					<td class="width-35">
						<form:radiobutton path="state" value="1" class="i-checks " checked="${checked}"/>使用
						<form:radiobutton path="state" value="0" class="i-checks "/>停用
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>手机号码：</label></td>
					<td class="width-35">
						<form:input path="mobile" htmlEscape="false" class="form-control required"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">部门：</label></td>
					<td class="width-35">
						<form:select path="department" class="form-control required">
							<form:option value="" label=""/>
							<form:options items="${fns:getDictList('sys_office_common')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
					<td class="width-15 active"><label class="pull-right">职务：</label></td>
					<td class="width-35">
						<form:select path="title" class="form-control required">
							<form:option value="" label=""/>
							<form:options items="${fns:getDictList('office_duties')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">办公室电话：</label></td>
					<td class="width-35">
						<form:input path="officePhone" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">微信号：</label></td>
					<td class="width-35">
						<form:input path="wxId" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">邮件地址：</label></td>
					<td class="width-35">
						<form:input path="email" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">QQ号码：</label></td>
					<td class="width-35">
						<form:input path="qqNumber" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">决策影响力：</label></td>
					<td class="width-35">
						<form:input path="influence" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">性格描述：</label></td>
					<td class="width-35">
						<form:input path="description" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">备注信息：</label></td>
					<td class="width-35" colspan="3">
						<form:textarea path="remarks" htmlEscape="false" rows="4"    class="form-control "/>
					</td>
				</tr>
		 	</tbody>
		</table>
	</form:form>
</body>
</html>