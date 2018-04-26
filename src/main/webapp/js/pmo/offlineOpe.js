$(function () {
	//加载线下运营列表
    loadOfflineOperList();
});


//线下运营列表
function loadOfflineOperList(){
	
    var queryUrl = path+'/service/offlineOper/query';
	//var queryUrl = path+'/json/test.json';
    var table = $('#OfflineOperList').bootstrapTable('destroy').bootstrapTable({
        url: queryUrl,                      //请求后台的URL（*）
        method: 'GET',                      //请求方式（*）
        toolbar: '#toolbar',              //工具按钮用哪个容器
        striped: true,                      //是否显示行间隔色
        fixedColumns: true,
        fixedNumber: 6,
        singleSelect : true,                // 单选checkbox 
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: true,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
        pageSize: 10,                     //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        search: false,                      //是否显示表格搜索
        strictSearch: true,
        showColumns: false,                  //是否显示所有的列（选择显示的列）
        showRefresh: true,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        //height: 500,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        showToggle: false,                   //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                  //是否显示父子表
        //得到查询的参数
        queryParams : function (params) {
        	return {
        		pageSize: params.limit,
        		pageNumber: params.offset/params.limit+1,
            };
        },
        columns: [
        {
        	checkbox: true,  
            visible: true                 //是否显示复选框  
        }, 
        {
            field: 'year',
            title: 'Year',
            sortable: true,
            formatter:function(value,row,index){
                return value+"年";
            }
        },
        {
            field: 'month',
            title: 'Month',
            sortable: true,
            formatter:function(value,row,index){
                return value+"月";
            }
        },
        {
            field: 'staffName',
            title: 'StaffName',
            sortable: true
        }, 
        {
            field: 'eHr',
            title: 'EHr',
            sortable: true
        },
        {
            field: 'chsoftiMskHours',
            title: 'MskHours',
            sortable: true
        },
        {
            field: 'chsoftiAwHours',
            title: 'AwHours',
            editable: {
                type: 'text',
                title: '中软实际工时',
                validate: function (v) {
                    if (!v) return '中软实际工时不能为空';
                    if (isNaN(v)) return '工时必须是数字';
                    var hours = parseInt(v);
                    if (hours <= 0) return '工时必须大于0';

                }
            },
            sortable: true
        }, 
        {
            field: 'chsoftiIwHours',
            title: 'IwHours',
            editable: {
                type: 'text',
                title: '中软无效工时',
                validate: function (v) {
                    if (!v) return '中软无效工时不能为空';
                    if (isNaN(v)) return '工时必须是数字';
                    var hours = parseInt(v);
                    if (hours <= 0) return '工时必须大于0';
                }
            },
            sortable: true
        },
        {
            field: 'chsoftiOtHours',
            title: 'OtHours',
            editable: {
                type: 'text',
                title: '中软加班费工时',
                validate: function (v) {
                    if (!v) return '中软加班费工时不能为空';
                    if (isNaN(v)) return '工时必须是数字';
                    var hours = parseInt(v);
                    if (hours <= 0) return '工时必须大于0';
                }
            },
            sortable: true
        },
        {
            field: 'chsoftiToHours',
            title: 'ToHours',
            editable: {
                type: 'text',
                title: '中软调休工时',
                validate: function (v) {
                    if (!v) return '中软调休工时不能为空';
                    if (isNaN(v)) return '工时必须是数字';
                    var hours = parseInt(v);
                    if (hours <= 0) return '工时必须大于0';
                }
            },
            sortable: true
        },
        {
            field: 'chsoftiApwHours',
            title: 'ApwHours',
            editable: {
                type: 'text',
                title: '中软调整上月工时',
                validate: function (v) {
                    if (!v) return '中软调整上月工时不能为空';
                    if (isNaN(v)) return '工时必须是数字';
                    var hours = parseInt(v);
                    if (hours <= 0) return '工时必须大于0';
                }
            },
            sortable: true
        },
        {
            field: 'chsoftiInfTravel',
            title: 'InfTravel',
            editable: {
                type: 'text',
                title: '差旅收入',
                validate: function (v) {
                    if (!v) return '差旅收入不能为空';
                    if (isNaN(v)) return '工时必须是数字';
                    var hours = parseInt(v);
                    if (hours <= 0) return '工时必须大于0';
                }
            },
            sortable: true
        },
        {
            field: 'chsoftiInfEquipment',
            title: 'InfEquipment',
            editable: {
                type: 'text',
                title: '付费设备收入',
                validate: function (v) {
                    if (!v) return '付费设备收入不能为空';
                    if (isNaN(v)) return '工时必须是数字';
                    var hours = parseInt(v);
                    if (hours <= 0) return '工时必须大于0';
                }
            },
            sortable: true
        },
        {
            field: 'chsoftiInfSub',
            title: 'InfSub',
            editable: {
                type: 'text',
                title: '分包收入',
                validate: function (v) {
                    if (!v) return '分包收入不能为空';
                    if (isNaN(v)) return '工时必须是数字';
                    var hours = parseInt(v);
                    if (hours <= 0) return '工时必须大于0';
                }
            },
            sortable: true
        },
        {
            field: 'chsoftiIfaw',//实际工时收入=员工单价*中软实际工时
            title: 'Ifaw',
            sortable: true,
            formatter: function (value, row, index) {  
            	if(row.chsoftiAwHours==null){
            		return 0;
            	}
            	var res = parseFloat(120)*parseFloat(row.chsoftiAwHours);
            	//计算后的值赋给字段
            	row.chsoftiIfaw=res;
            	return res;
            }
        },
        {
            field: 'chsoftiInvalid',//无效工时收入=员工单价*中软无效工时
            title: 'Invalid',
            sortable: true,
            formatter: function (value, row, index) {  
            	if(row.chsoftiIwHours==null){
            		return 0;
            	}
            	var res = parseFloat(120)*parseFloat(row.chsoftiIwHours);
            	//计算后的值赋给字段
            	row.chsoftiInvalid=res;
            	return res;
            }
        },
        {
            field: 'chsoftiInfOt',//加班费工时收入=员工单价*中软加班费工时
            title: 'InfOt',
            sortable: true,
            formatter: function (value, row, index) {  
            	if(row.chsoftiOtHours==null){
            		return 0;
            	}
            	var res = parseFloat(120)*parseFloat(row.chsoftiOtHours);
            	//计算后的值赋给字段
            	row.chsoftiInfOt=res;
            	return res;
            }
        },
        {
            field: 'chsoftiInfPt',//调休工时收入=员工单价*中软调休工时
            title: 'InfPt',
            sortable: true,
            formatter: function (value, row, index) {  
            	if(row.chsoftiToHours==null){
            		return 0;
            	}
            	var res = parseFloat(120)*parseFloat(row.chsoftiToHours);
            	//计算后的值赋给字段
            	row.chsoftiInfPt=res;
            	return res;
            }
        },
        {
            field: 'chsoftiInfAd',//调整上月工时收入=员工单价*中软调整上月工时
            title: 'InfAd',
            sortable: true,
            formatter: function (value, row, index) {  
            	if(row.chsoftiApwHours==null){
            		return 0;
            	}
            	var res = parseFloat(120)*parseFloat(row.chsoftiApwHours);
            	//计算后的值赋给字段
            	row.chsoftiInfAd=res;
            	return res;
            }
        },
        {
            field: 'chsoftiInfTotal',//月收入合计=实际工时收入+无效工时收入+加班费工时收入+调休工时收入+调整上月工时收入+差旅收入+付费设备收入+分包收入
            title: 'InfTotal',
            sortable: true,
            formatter: function (value, row, index) {  
            	var res = row.chsoftiIfaw+row.chsoftiInvalid+
            	row.chsoftiInfOt+row.chsoftiInfPt+
            	row.chsoftiInfAd+row.chsoftiInfTravel+
            	row.chsoftiInfEquipment+row.chsoftiInfSub;
            	return res;
            	//计算后的值赋给字段
            	row.chsoftiInfTotal=res;
            }
        },
        {
            field: 'chsoftiInfCurrent',//当月有效收入=月收入合计-无效工时收入
            title: 'InfCurrent',
            sortable: true,
            formatter: function (value, row, index) {  
            	var res = row.chsoftiInfTotal-row.chsoftiInvalid;
            	//计算后的值赋给字段
            	row.chsoftiInfCurrent=res;
            	return res;
            }
        },
        {
            field: 'chsoftiEffectiveNr',//有效NR=当月有效收入/1.06
            title: 'EffectiveNr',
            sortable: true,
            formatter: function (value, row, index) {  
            	var res = row.chsoftiInfCurrent/1.06;
            	//计算后的值赋给字段
            	//row.chsoftiEffectiveNr=res;
            	return res;
            }
        },
        {
            field: 'chsoftiEffectiveSt',//当月有效人力=中软实际工时/中软月标准工时
            title: 'EffectiveSt',
            sortable: true,
            formatter: function (value, row, index) {  
            	var res = row.chsoftiIfaw/1;
            	//计算后的值赋给字段
            	row.chsoftiEffectiveSt=res;
            	return res;
            }
        },
        {
            field: 'chsoftiInvalidSt',//当月无效人力=中软无效工时/中软月标准工时
            title: 'InvalidSt',
            sortable: true,
            formatter: function (value, row, index) {  
            	var res = row.chsoftiInvalid/1;
            	//计算后的值赋给字段
            	row.chsoftiInvalidSt=res;
            	return res;
            }
        }
        /*{
            field:'id',
            title: 'Operation',
            width: 120,
            align: 'center',
            valign: 'middle'
        }*/],
        onEditableSave: function (field, row, oldValue, $el) {
        	//console.log(row);
        	//console.log(oldValue);
            $.ajax({
                type: "POST",
                url: path+"/service/offlineOper/save",
                contentType: "application/json;charset=utf-8",
                data: JSON.stringify(row),
                dataType: 'JSON',
                success: function (data, status) {
                    if (data == "0") {
                    	//刷新表格
                    	$('#OfflineOperList').bootstrapTable('refresh');  
                    }
                },
                error: function () {
                    alert('编辑失败');
                },
                complete: function () {

                }

            });
        },
        onLoadSuccess: function () {
        },
        onLoadError: function () {
        	
        },
        onDblClickRow: function (row, $element) {
           
        },
        
    });
}

function search(){
	//获取查询条件
	var staffName = $("#staffName").val();
	var ehr = $("#ehr").val();
	var queryParams = { 
		query: {  
		   staffName:staffName,
		   eHr:ehr
        }
    }  
	//刷新表格  
    $('#OfflineOperList').bootstrapTable('refresh',queryParams);  
}

