<%@ page contentType="text/html;charset=UTF-8" %>
<script>
$(document).ready(function() {
	$('#opportunityTable').bootstrapTable({
		 
		  //请求方法
               method: 'post',
               //类型json
               dataType: "json",
               contentType: "application/x-www-form-urlencoded",
               //显示检索按钮
	           showSearch: true,
               //显示刷新按钮
               showRefresh: true,
               //显示切换手机试图按钮
               showToggle: true,
               //显示 内容列下拉框
    	       showColumns: true,
    	       //显示到处按钮
    	       showExport: true,
    	       //显示切换分页按钮
    	       showPaginationSwitch: true,
    	       //最低显示2行
    	       minimumCountColumns: 2,
               //是否显示行间隔色
               striped: true,
               //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）     
               cache: false,    
               //是否显示分页（*）  
               pagination: true,   
                //排序方式 
               sortOrder: "asc",  
               //初始化加载第一页，默认第一页
               pageNumber:1,   
               //每页的记录行数（*）   
               pageSize: 10,  
               //可供选择的每页的行数（*）    
               pageList: [10, 25, 50, 100],
               //这个接口需要处理bootstrap table传递的固定参数,并返回特定格式的json数据  
               url: "${ctx}/opportunity/opportunity/data",
               //默认值为 'limit',传给服务端的参数为：limit, offset, search, sort, order Else
               //queryParamsType:'',   
               ////查询参数,每次调用是会带上这个参数，可自定义                         
               queryParams : function(params) {
               	var searchParam = $("#searchForm").serializeJSON();
               	searchParam.pageNo = params.limit === undefined? "1" :params.offset/params.limit+1;
               	searchParam.pageSize = params.limit === undefined? -1 : params.limit;
               	searchParam.orderBy = params.sort === undefined? "" : params.sort+ " "+  params.order;
                   return searchParam;
               },
               //分页方式：client客户端分页，server服务端分页（*）
               sidePagination: "server",
               contextMenuTrigger:"right",//pc端 按右键弹出菜单
               contextMenuTriggerMobile:"press",//手机端 弹出菜单，click：单击， press：长按。
               contextMenu: '#context-menu',
               onContextMenuItem: function(row, $el){
                   if($el.data("item") == "edit"){
                   		edit(row.id);
                   }else if($el.data("item") == "view"){
                       view(row.id);
                   } else if($el.data("item") == "delete"){
                        jp.confirm('确认要删除该线索商机记录吗？', function(){
                       	jp.loading();
                       	jp.get("${ctx}/opportunity/opportunity/delete?id="+row.id, function(data){
                   	  		if(data.success){
                   	  			$('#opportunityTable').bootstrapTable('refresh');
                   	  			jp.success(data.msg);
                   	  		}else{
                   	  			jp.error(data.msg);
                   	  		}
                   	  	})
                   	   
                   	});
                      
                   } 
               },
              
               onClickRow: function(row, $el){
               },
               	onShowSearch: function () {
			$("#search-collapse").slideToggle();
		},
               columns: [{
		        checkbox: true
		       
		    }
			,{
                       title: '序号',
                       field: '',
                       align:'center',
                       formatter: function (value, row, index) {
                           return index+1;
                       }
                   },{
                       field: 'customer.name',
                       title: '客户名称',
                       sortable: true,
                       sortName: 'customer.name'

                   },
				   {
		        field: 'oppNo',
		        title: '商机编号',
		        sortable: true,
		        sortName: 'oppNo'
		        ,formatter:function(value, row , index){
		        	value = jp.unescapeHTML(value);
				   <c:choose>
					   <c:when test="${fns:hasPermission('opportunity:opportunity:edit')}">
					      return "<a href='javascript:edit(\""+row.id+"\")'>"+value+"</a>";
				      </c:when>
					  <c:when test="${fns:hasPermission('opportunity:opportunity:view')}">
					      return "<a href='javascript:view(\""+row.id+"\")'>"+value+"</a>";
				      </c:when>
					  <c:otherwise>
					      return value;
				      </c:otherwise>
				   </c:choose>
		         }
		       
		    }
			,{
		        field: 'oppName',
		        title: '商机名称',
		        sortable: true,
		        sortName: 'oppName'
		       
		    },{
                       field: 'oppStageId',
                       title: '商机阶段',
                       sortable: true,
                       sortName: 'oppStageId',
					   formatter:function(value, row , index){
                       return jp.getDictLabel(${fns:toJson(fns:getDictList('business_opportunity_stage'))}, value, "-");
					}

             }
             ,{
                       field: 'company.name',
                       title: '所属公司',
                       sortable: true,
                       sortName: 'company.name'

            },{
                       field: 'office.name',
                       title: '所属部门',
                       sortable: true,
                       sortName: 'office.name'

             }
              ,{
                       field: 'empId',
                       title: '跟进人员',
                       sortable: true,
                       sortName: 'empId'

              },{
                       field: 'lastTime',
                       title: '上次跟进时间',
                       sortable: true,
                       sortName: 'lastTime'

              }
			,{
		        field: 'sourceId',
		        title: '商机来源',
		        sortable: true,
		        sortName: 'sourceId',
				formatter:function(value, row , index){
					return jp.getDictLabel(${fns:toJson(fns:getDictList('customer_source'))}, value, "-");
				}

		       
		    }
			,{
		        field: 'status',
		        title: '商机状态',
		        sortable: true,
		        sortName: 'status',
                 formatter:function(value, row , index){
                           return jp.getDictLabel(${fns:toJson(fns:getDictList('business_opportunity_state'))}, value, "-");
                 }
		       
		    }
			,{
		        field: 'demand',
		        title: '需求描述',
		        sortable: true,
		        sortName: 'demand'

		    }
		     ]
		
		});
		
		  
	  if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端

		 
		  $('#opportunityTable').bootstrapTable("toggleView");
		}
	  
	  $('#opportunityTable').on('check.bs.table uncheck.bs.table load-success.bs.table ' +
                'check-all.bs.table uncheck-all.bs.table', function () {
            $('#remove').prop('disabled', ! $('#opportunityTable').bootstrapTable('getSelections').length);
            $('#view,#edit,#delay,#follow-up,#follow-up-record').prop('disabled', $('#opportunityTable').bootstrapTable('getSelections').length!=1);
        });
		  
		$("#btnImport").click(function(){
			jp.open({
			    type: 2,
                area: [500, 200],
                auto: true,
			    title:"导入数据",
			    content: "${ctx}/tag/importExcel" ,
			    btn: ['下载模板','确定', '关闭'],
				    btn1: function(index, layero){
					  jp.downloadFile('${ctx}/opportunity/opportunity/import/template');
				  },
			    btn2: function(index, layero){
				        var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
						iframeWin.contentWindow.importExcel('${ctx}/opportunity/opportunity/import', function (data) {
							if(data.success){
								jp.success(data.msg);
								refresh();
							}else{
								jp.error(data.msg);
							}
					   		jp.close(index);
						});//调用保存事件
						return false;
				  },
				 
				  btn3: function(index){ 
					  jp.close(index);
	    	       }
			}); 
		});
		
		
	 $("#export").click(function(){//导出Excel文件
	        var searchParam = $("#searchForm").serializeJSON();
	        searchParam.pageNo = 1;
	        searchParam.pageSize = -1;
            var sortName = $('#opportunityTable').bootstrapTable("getOptions", "none").sortName;
            var sortOrder = $('#opportunityTable').bootstrapTable("getOptions", "none").sortOrder;
            var values = "";
            for(var key in searchParam){
                values = values + key + "=" + searchParam[key] + "&";
            }
            if(sortName != undefined && sortOrder != undefined){
                values = values + "orderBy=" + sortName + " "+sortOrder;
            }

			jp.downloadFile('${ctx}/opportunity/opportunity/export?'+values);
	  })

		    
	  $("#search").click("click", function() {// 绑定查询按扭
		  $('#opportunityTable').bootstrapTable('refresh');
		});
	 
	 $("#reset").click("click", function() {// 绑定查询按扭
		  $("#searchForm  input").val("");
		  $("#searchForm  select").val("");
		  $("#searchForm  .select-item").html("");
		  $('#opportunityTable').bootstrapTable('refresh');
		});
		
		
	});
		
  function getIdSelections() {
        return $.map($("#opportunityTable").bootstrapTable('getSelections'), function (row) {
            return row.id
        });
    }
  
  function deleteAll(){

		jp.confirm('确认要删除该线索商机记录吗？', function(){
			jp.loading();  	
			jp.get("${ctx}/opportunity/opportunity/deleteAll?ids=" + getIdSelections(), function(data){
         	  		if(data.success){
         	  			$('#opportunityTable').bootstrapTable('refresh');
         	  			jp.success(data.msg);
         	  		}else{
         	  			jp.error(data.msg);
         	  		}
         	  	})
          	   
		})
  }

    //刷新列表
  function refresh(){
  	$('#opportunityTable').bootstrapTable('refresh');
  }
  
   function add(){
	  jp.openSaveDialog('新增线索商机', "${ctx}/opportunity/opportunity/form",'1000px', '700px');
  }


  
   function edit(id){//没有权限时，不显示确定按钮
       if(id == undefined){
	      id = getIdSelections();
	}
	jp.openSaveDialog('编辑线索商机', "${ctx}/opportunity/opportunity/form?id=" + id, '1000px', '700px');
  }
  
 function view(id){//没有权限时，不显示确定按钮
      if(id == undefined){
             id = getIdSelections();
      }
        jp.openViewDialog('查看线索商机', "${ctx}/opportunity/opportunity/form?id=" + id,'1000px', '700px');
 }
//延时申请
function delay(id) {
    if(id == undefined){
        id = getIdSelections();
    }
    jp.alert("待开发中");
}
 //跟进
function follow_up(id) {
    if(id == undefined){
        id = getIdSelections();
    }
    jp.openSaveDialog('商机跟进', "${ctx}/oppactivities/oppActivities/form?oppId="+id,'800px', '500px');
}
function followUpRecord(id) {
    if(id == undefined){
        id = getIdSelections();
    }
    jp.openViewDialog('商机跟进记录列表', "${ctx}/oppactivities/oppActivities/list?oppId=" + id,'1200px', '600px');
}

</script>