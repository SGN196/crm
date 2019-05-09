<%@ page contentType="text/html;charset=UTF-8" %>
<script>
$(document).ready(function() {
	$('#customerTable').bootstrapTable({
		 
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
               url: "${ctx}/customer/customer/data",
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
                        jp.confirm('确认要删除该客户档案记录吗？', function(){
                       	jp.loading();
                       	jp.get("${ctx}/customer/customer/delete?id="+row.id, function(data){
                   	  		if(data.success){
                   	  			$('#customerTable').bootstrapTable('refresh');
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
			/*,{
		        field: 'erpcusId',
		        title: 'ERP系统客户Id',
		        sortable: true,
		        sortName: 'erpcusId'

		    }*/
			,{
		        field: 'orgId',
		        title: '所属公司Id',
		        sortable: true,
		        sortName: 'orgId'
		       
		    }
			,{
		        field: 'deptId',
		        title: '所属部门Id',
		        sortable: true,
		        sortName: 'deptId'
		       
		    }
			,{
		        field: 'empId',
		        title: '业务员Id',
		        sortable: true,
		        sortName: 'empId'
		       
		    }
			/*,{
		        field: 'number',
		        title: '客户代码',
		        sortable: true,
		        sortName: 'number'
		       
		    }*/
			,{
		        field: 'name',
		        title: '客户名称',
		        sortable: true,
		        sortName: 'name'
				,formatter:function(value, row , index){
				   value = jp.unescapeHTML(value);
				<c:choose>
				   <c:when test="${fns:hasPermission('customer:customer:edit')}">
				   return "<a href='javascript:edit(\""+row.id+"\")'>"+value+"</a>";
				</c:when>
				   <c:when test="${fns:hasPermission('customer:customer:view')}">
				   return "<a href='javascript:view(\""+row.id+"\")'>"+value+"</a>";
				</c:when>
				   <c:otherwise>
				   return value;
				</c:otherwise>
				   </c:choose>
				}
		       
		    }
			/*,{
		        field: 'state',
		        title: '使用状态',
		        sortable: true,
		        sortName: 'state'
		       
		    }
			,{
		        field: 'isPublic',
		        title: '是否公海',
		        sortable: true,
		        sortName: 'isPublic'
		       
		    }*/
			,{
		        field: 'sourceId',
		        title: '客户来源Id',
		        sortable: true,
		        sortName: 'sourceId'
		       
		    }
			,{
		        field: 'statusId',
		        title: '跟进状态Id',
		        sortable: true,
		        sortName: 'statusId',
		        formatter:function(value, row , index){
		        	return jp.getDictLabel(${fns:toJson(fns:getDictList(''))}, value, "-");
		        }
		       
		    }
			/*,{
		        field: 'areaId',
		        title: '区域Id',
		        sortable: true,
		        sortName: 'areaId'
		       
		    }
			,{
		        field: 'address',
		        title: '客户地址',
		        sortable: true,
		        sortName: 'address'
		       
		    }
			,{
		        field: 'contactId',
		        title: '主联系人Id',
		        sortable: true,
		        sortName: 'contactId'
		       
		    }
			,{
		        field: 'provincelId',
		        title: '省份Id',
		        sortable: true,
		        sortName: 'provincelId'
		       
		    }
			,{
		        field: 'cityId',
		        title: '城市Id',
		        sortable: true,
		        sortName: 'cityId'
		       
		    }
			,{
		        field: 'countryId',
		        title: '区县Id',
		        sortable: true,
		        sortName: 'countryId'
		       
		    }
			,{
		        field: 'streetId',
		        title: '镇街Id',
		        sortable: true,
		        sortName: 'streetId'
		       
		    }
			,{
		        field: 'detailAddress',
		        title: '详细地址',
		        sortable: true,
		        sortName: 'detailAddress'
		       
		    }
			,{
		        field: 'gps',
		        title: 'GPS定位',
		        sortable: true,
		        sortName: 'gps'
		       
		    }
			,{
		        field: 'shortName',
		        title: '简称',
		        sortable: true,
		        sortName: 'shortName'
		       
		    }*/
			,{
		        field: 'industoryId',
		        title: '行业Id',
		        sortable: true,
		        sortName: 'industoryId',
		        formatter:function(value, row , index){
		        	return jp.getDictLabel(${fns:toJson(fns:getDictList(''))}, value, "-");
		        }
		       
		    }
			,{
		        field: 'cusLevel',
		        title: '客户等级',
		        sortable: true,
		        sortName: 'cusLevel',
		        formatter:function(value, row , index){
		        	return jp.getDictLabel(${fns:toJson(fns:getDictList(''))}, value, "-");
		        }
		       
		    }
			/*,{
		        field: 'fax',
		        title: '传真',
		        sortable: true,
		        sortName: 'fax'
		       
		    }
			,{
		        field: 'webSite',
		        title: '官网地址',
		        sortable: true,
		        sortName: 'webSite'
		       
		    }
			,{
		        field: 'email',
		        title: '官方邮箱',
		        sortable: true,
		        sortName: 'email'
		       
		    }
			,{
		        field: 'bank',
		        title: '开户行',
		        sortable: true,
		        sortName: 'bank'
		       
		    }
			,{
		        field: 'account',
		        title: '银行账号',
		        sortable: true,
		        sortName: 'account'
		       
		    }
			,{
		        field: 'propertyId',
		        title: '企业性质Id',
		        sortable: true,
		        sortName: 'propertyId'
		       
		    }
			,{
		        field: 'creditCode',
		        title: '统一信用代码',
		        sortable: true,
		        sortName: 'creditCode'
		       
		    }
			,{
		        field: 'business',
		        title: '经营范围',
		        sortable: true,
		        sortName: 'business'
		       
		    }
			,{
		        field: 'recent',
		        title: '经营近况',
		        sortable: true,
		        sortName: 'recent'
		       
		    }
			,{
		        field: 'legalPerson',
		        title: '客户法人',
		        sortable: true,
		        sortName: 'legalPerson'
		       
		    }
			,{
		        field: 'usedName',
		        title: '曾用名',
		        sortable: true,
		        sortName: 'usedName'
		       
		    }
			,{
		        field: 'competitor',
		        title: '竞争对手',
		        sortable: true,
		        sortName: 'competitor'
		       
		    }
			,{
		        field: 'coreAdvantage',
		        title: '核心优势',
		        sortable: true,
		        sortName: 'coreAdvantage'
		       
		    }
			,{
		        field: 'industryPosition',
		        title: '行业地位',
		        sortable: true,
		        sortName: 'industryPosition'
		       
		    }
			,{
		        field: 'industryStandard',
		        title: '行业标准',
		        sortable: true,
		        sortName: 'industryStandard'
		       
		    }
			,{
		        field: 'turnoverYearly',
		        title: '年营业额',
		        sortable: true,
		        sortName: 'turnoverYearly'
		       
		    }
			,{
		        field: 'taxYearly',
		        title: '年纳税额',
		        sortable: true,
		        sortName: 'taxYearly'
		       
		    }
			,{
		        field: 'workerNum',
		        title: '职工总人数',
		        sortable: true,
		        sortName: 'workerNum'
		       
		    }
			,{
		        field: 'researcherNum',
		        title: '科研人员人数',
		        sortable: true,
		        sortName: 'researcherNum'
		       
		    }
			,{
		        field: 'rateA',
		        title: '科研人员占比',
		        sortable: true,
		        sortName: 'rateA'
		       
		    }
			,{
		        field: 'juniorNum',
		        title: '大专科研人数',
		        sortable: true,
		        sortName: 'juniorNum'
		       
		    }
			,{
		        field: 'undergraduateNum',
		        title: '本科科研人数',
		        sortable: true,
		        sortName: 'undergraduateNum'
		       
		    }
			,{
		        field: 'masterNum',
		        title: '硕士科研人数',
		        sortable: true,
		        sortName: 'masterNum'
		       
		    }
			,{
		        field: 'primaryNum',
		        title: '初职科研人数',
		        sortable: true,
		        sortName: 'primaryNum'
		       
		    }
			,{
		        field: 'intermediateNum',
		        title: '中职科研人数',
		        sortable: true,
		        sortName: 'intermediateNum'
		       
		    }
			,{
		        field: 'intellectualFunds',
		        title: '知识产权经费',
		        sortable: true,
		        sortName: 'intellectualFunds'
		       
		    }
			,{
		        field: 'rateB',
		        title: '经费占比',
		        sortable: true,
		        sortName: 'rateB'
		       
		    }
			,{
		        field: 'inventionPatentNum',
		        title: '发明专利数量',
		        sortable: true,
		        sortName: 'inventionPatentNum'
		       
		    }
			,{
		        field: 'appearancePatentNum',
		        title: '外观专利数量',
		        sortable: true,
		        sortName: 'appearancePatentNum'
		       
		    }
			,{
		        field: 'trademarkNum',
		        title: '商标版权数量',
		        sortable: true,
		        sortName: 'trademarkNum'
		       
		    }
			,{
		        field: 'practicalPatentNum',
		        title: '实用型专利数量',
		        sortable: true,
		        sortName: 'practicalPatentNum'
		       
		    }*/
			,{
		        field: 'remarks',
		        title: '备注信息',
		        sortable: true,
		        sortName: 'remarks'
		       
		    }
		     ]
		
		});
		
		  
	  if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端

		 
		  $('#customerTable').bootstrapTable("toggleView");
		}
	  
	  $('#customerTable').on('check.bs.table uncheck.bs.table load-success.bs.table ' +
                'check-all.bs.table uncheck-all.bs.table', function () {
            $('#remove').prop('disabled', ! $('#customerTable').bootstrapTable('getSelections').length);
            $('#view,#edit').prop('disabled', $('#customerTable').bootstrapTable('getSelections').length!=1);
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
					  jp.downloadFile('${ctx}/customer/customer/import/template');
				  },
			    btn2: function(index, layero){
				        var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
						iframeWin.contentWindow.importExcel('${ctx}/customer/customer/import', function (data) {
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
            var sortName = $('#customerTable').bootstrapTable("getOptions", "none").sortName;
            var sortOrder = $('#customerTable').bootstrapTable("getOptions", "none").sortOrder;
            var values = "";
            for(var key in searchParam){
                values = values + key + "=" + searchParam[key] + "&";
            }
            if(sortName != undefined && sortOrder != undefined){
                values = values + "orderBy=" + sortName + " "+sortOrder;
            }

			jp.downloadFile('${ctx}/customer/customer/export?'+values);
	  })

		    
	  $("#search").click("click", function() {// 绑定查询按扭
		  $('#customerTable').bootstrapTable('refresh');
		});
	 
	 $("#reset").click("click", function() {// 绑定查询按扭
		  $("#searchForm  input").val("");
		  $("#searchForm  select").val("");
		  $("#searchForm  .select-item").html("");
		  $('#customerTable').bootstrapTable('refresh');
		});
		
		
	});
		
  function getIdSelections() {
        return $.map($("#customerTable").bootstrapTable('getSelections'), function (row) {
            return row.id
        });
    }
  
  function deleteAll(){

		jp.confirm('确认要删除该客户档案记录吗？', function(){
			jp.loading();  	
			jp.get("${ctx}/customer/customer/deleteAll?ids=" + getIdSelections(), function(data){
         	  		if(data.success){
         	  			$('#customerTable').bootstrapTable('refresh');
         	  			jp.success(data.msg);
         	  		}else{
         	  			jp.error(data.msg);
         	  		}
         	  	})
          	   
		})
  }

    //刷新列表
  function refresh(){
  	$('#customerTable').bootstrapTable('refresh');
  }
  
   function add(){
	  jp.openSaveDialog('新增客户档案', "${ctx}/customer/customer/form",'800px', '500px');
  }


  
   function edit(id){//没有权限时，不显示确定按钮
       if(id == undefined){
	      id = getIdSelections();
	}
	jp.openSaveDialog('编辑客户档案', "${ctx}/customer/customer/form?id=" + id, '800px', '500px');
  }
  
 function view(id){//没有权限时，不显示确定按钮
      if(id == undefined){
             id = getIdSelections();
      }
        jp.openViewDialog('查看客户档案', "${ctx}/customer/customer/form?id=" + id, '800px', '500px');
 }



</script>