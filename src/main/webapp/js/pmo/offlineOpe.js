$(function () {
	//加载线下运营列表
    loadOfflineOperList();
});

function loadOfflineSummary(data){
	var s1=0.00;//实际工时
	var s2=0.00;//无效工时
	var s3=0.00;//加班工时
	var s4=0.00;//调休工时
	var s5=0.00;//调整上月工时
	var s6=0.00;//实际工时收入
	var s7=0.00;//无效工时收入
	var s8=0.00;//加班工时收入
	var s9=0.00;//调休工时收入
	var s10=0.00;//调整上月工时收入
	var s11=0.00;//差旅收入
	var s12=0.00;//付费设备收入
	var s13=0.00;//分包收入
	var s14=0.00;//月收入合计-原币种
	var s15=0.00;//月收入合计-人民币
	var s16=0.00;//当月有效收入
	var s17=0.00;//有效NR
	var s18=0.00;//当月有效人力
	var s19=0.00;//当月无效人力
	
	for(var i=0;i<data.length;i++){
		if(data[i].chsoftiAwHours!=null){
			//实际工时
			s1 = floatObj.add(s1, data[i].chsoftiAwHours, 2, 'add');
		}
        if(data[i].chsoftiIwHours!=null){
        	//无效工时
    		s2 = floatObj.add(s2, data[i].chsoftiIwHours, 2, 'add');
		}
        if(data[i].chsoftiOtHours!=null){
        	//加班工时
    		s3 = floatObj.add(s3, data[i].chsoftiOtHours, 2, 'add');
        }
        if(data[i].chsoftiToHours!=null){
        	//调休工时
    		s4 = floatObj.add(s4, data[i].chsoftiToHours, 2, 'add');
        }
        if(data[i].chsoftiApwHours!=null){
        	//调整上月工时
    		s5 = floatObj.add(s5, data[i].chsoftiApwHours, 2, 'add');
        }
        if(data[i].chsoftiIfaw!=null){
        	//实际工时收入
    		s6 = floatObj.add(s6, data[i].chsoftiIfaw, 2, 'add');
        }
        if(data[i].chsoftiInvalid!=null){
        	//无效工时收入
    		s7 = floatObj.add(s7, data[i].chsoftiInvalid, 2, 'add');
        }
        if(data[i].chsoftiInfOt!=null){
        	//加班工时收入
    		s8 = floatObj.add(s8, data[i].chsoftiInfOt, 2, 'add');
        }
        if(data[i].chsoftiInfPt!=null){
        	//调休工时收入
    		s9 = floatObj.add(s9, data[i].chsoftiInfPt, 2, 'add');
        }
        if(data[i].chsoftiInfAd!=null){
        	//调整上月工时收入
    		s10 = floatObj.add(s10, data[i].chsoftiInfAd, 2, 'add');
        }
        if(data[i].chsoftiInfTravel!=null){
        	//差旅收入
    		s11 = floatObj.add(s11, data[i].chsoftiInfTravel, 2, 'add');
        }
        if(data[i].chsoftiInfEquipment!=null){
        	//付费设备收入
    		s12 = floatObj.add(s12, data[i].chsoftiInfEquipment, 2, 'add');
        }
        if(data[i].chsoftiInfSub!=null){
        	//分包收入
    		s13 = floatObj.add(s13, data[i].chsoftiInfSub, 2, 'add');
        }
        if(data[i].chsoftiInfTotal!=null){
        	//月收入合计-原币种
    		s14 = floatObj.add(s14, data[i].chsoftiInfTotal, 2, 'add');
        }
        if(data[i].chsoftiInfRmbtotal!=null){
        	//月收入合计-人民币
    		s15 = floatObj.add(s15, data[i].chsoftiInfRmbtotal, 2, 'add');
        }
        if(data[i].chsoftiInfCurrent!=null){
        	//当月有效收入
    		s16 = floatObj.add(s16, data[i].chsoftiInfCurrent, 2, 'add');
        }
        if(data[i].chsoftiEffectiveNr!=null){
        	//有效NR
    		s17 = floatObj.add(s17, data[i].chsoftiEffectiveNr, 2, 'add');
        }
        if(data[i].chsoftiEffectiveSt!=null){
        	//当月有效人力
    		s18 = floatObj.add(s18, data[i].chsoftiEffectiveSt, 2, 'add');
        }
        if(data[i].chsoftiInvalidSt!=null){
        	//当月无效人力
    		s19 = floatObj.add(s19, data[i].chsoftiInvalidSt, 2, 'add');
        }
	}
	
	$("#s1").html(s1.toFixed(2));//实际工时
	$("#s2").html(s2.toFixed(2));//无效工时
	$("#s3").html(s3.toFixed(2));//加班工时
	$("#s4").html(s4.toFixed(2));//调休工时
	$("#s5").html(s5.toFixed(2));//调整上月工时
	$("#s6").html(s6.toFixed(2));//实际工时收入
	$("#s7").html(s7.toFixed(2));//无效工时收入
	$("#s8").html(s8.toFixed(2));//加班工时收入
	$("#s9").html(s9.toFixed(2));//调休工时收入
	$("#s10").html(s10.toFixed(2));//调整上月工时收入
	$("#s11").html(s11.toFixed(2));//差旅收入
	$("#s12").html(s12.toFixed(2));//付费设备收入
	$("#s13").html(s13.toFixed(2));//分包收入
	$("#s14").html(s14.toFixed(2));//月收入合计-原币种
	$("#s15").html(s15.toFixed(2));//月收入合计-人民币
	$("#s16").html(s16.toFixed(2));//当月有效收入
	$("#s17").html(s17.toFixed(2));//有效NR
	$("#s18").html(s18.toFixed(2));//当月有效人力
	$("#s19").html(s19.toFixed(2));//当月无效人力
	
}




//线下运营列表
function loadOfflineOperList(){
	
    var queryUrl = path+'/service/offlineOper/query';
	//var queryUrl = path+'/json/test.json';
    var table = $('#OfflineOperList').bootstrapTable({
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
        pageSize: 200,                     //每页的记录行数（*）
        pageList: [200],        //可供选择的每页的行数（*）
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
                    if (hours < 0) return '工时必须大于等于0';
                    
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
                    if (hours < 0) return '工时必须大于等于0';
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
                    if (hours < 0) return '工时必须大于等于0';
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
                    if (hours < 0) return '工时必须大于等于0';
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
                    //if (hours <= 0) return '工时必须大于0';
                }
            },
            sortable: true
        },
        {
            field: 'chsoftiInfTravel',
            title: 'Travel',
            editable: {
                type: 'text',
                title: '差旅收入',
                validate: function (v) {
                    if (!v) return '差旅收入不能为空';
                    if (isNaN(v)) return '差旅收入必须是数字';
                    var hours = parseInt(v);
                    if (hours < 0) return '差旅收入必须大于等于0';
                }
            },
            sortable: true
        },
        {
            field: 'chsoftiInfEquipment',
            title: 'Equipment',
            editable: {
                type: 'text',
                title: '付费设备收入',
                validate: function (v) {
                    if (!v) return '付费设备收入不能为空';
                    if (isNaN(v)) return '付费设备收入必须是数字';
                    var hours = parseInt(v);
                    if (hours < 0) return '付费设备收入必须大于等于0';
                }
            },
            sortable: true
        },
        {
            field: 'chsoftiInfSub',
            title: 'Sub',
            editable: {
                type: 'text',
                title: '分包收入',
                validate: function (v) {
                    if (!v) return '分包收入不能为空';
                    if (isNaN(v)) return '分包收入必须是数字';
                    var hours = parseInt(v);
                    if (hours < 0) return '分包收入必须大于等于0';
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
            		return "<font color='green' family='黑体'><strong>0</strong></font>";
            	}
            	var res = floatObj.multiply(row.billRate, row.chsoftiAwHours, 2, 'multiply');
            	
            	//计算后的值赋给字段
            	row.chsoftiIfaw=res.toFixed(2);;
            	return "<font color='green' family='黑体'><strong>"+res.toFixed(2);+"</strong></font>";
            }
        },
        {
            field: 'chsoftiInvalid',//无效工时收入=员工单价*中软无效工时
            title: 'Invalid',
            sortable: true,
            formatter: function (value, row, index) {  
            	if(row.chsoftiIwHours==null){
            		return "<font color='green' family='黑体'><strong>0</strong></font>";
            	}
            	var res = floatObj.multiply(row.billRate, row.chsoftiIwHours, 2, 'multiply');
            	
            	//计算后的值赋给字段
            	row.chsoftiInvalid=res.toFixed(2);;
            	return "<font color='green' family='黑体'><strong>"+res.toFixed(2);+"</strong></font>";
            }
        },
        {
            field: 'chsoftiInfOt',//加班费工时收入=员工单价*中软加班费工时
            title: 'InfOt',
            sortable: true,
            formatter: function (value, row, index) {  
            	if(row.chsoftiOtHours==null){
            		return "<font color='green' family='黑体'><strong>0</strong></font>";
            	}
            	var res = floatObj.multiply(row.billRate, row.chsoftiOtHours, 2, 'multiply');
            	
            	//计算后的值赋给字段
            	row.chsoftiInfOt=res.toFixed(2);;
            	return "<font color='green' family='黑体'><strong>"+res.toFixed(2);+"</strong></font>";
            }
        },
        {
            field: 'chsoftiInfPt',//调休工时收入=员工单价*中软调休工时
            title: 'InfPt',
            sortable: true,
            formatter: function (value, row, index) {  
            	if(row.chsoftiToHours==null){
            		return "<font color='green' family='黑体'><strong>0</strong></font>";
            	}
            	var res = floatObj.multiply(row.billRate, row.chsoftiToHours, 2, 'multiply');
            	
            	//计算后的值赋给字段
            	row.chsoftiInfPt=res.toFixed(2);;
            	return "<font color='green' family='黑体'><strong>"+res.toFixed(2);+"</strong></font>";
            }
        },
        {
            field: 'chsoftiInfAd',//调整上月工时收入=员工单价*中软调整上月工时
            title: 'InfAd',
            sortable: true,
            formatter: function (value, row, index) {  
            	if(row.chsoftiApwHours==null){
            		return "<font color='green' family='黑体'><strong>0</strong></font>";
            	}
            	var res = floatObj.multiply(row.billRate, row.chsoftiApwHours, 2, 'multiply');
            	//计算后的值赋给字段
            	row.chsoftiInfAd=res.toFixed(2);;
            	return "<font color='green' family='黑体'><strong>"+res.toFixed(2);+"</strong></font>";
            }
        },
        {
            field: 'chsoftiInfTotal',//月收入合计-原币种=实际工时收入+无效工时收入+加班费工时收入+调休工时收入+调整上月工时收入+差旅收入+付费设备收入+分包收入
            title: 'InfTotal',
            sortable: true,
            formatter: function (value, row, index) {  
            	var temp = 0;
            	//中软实际工时收入
            	if(row.chsoftiIfaw!=null){
            		temp=floatObj.add(temp, row.chsoftiIfaw, 2, 'add');
            	}
            	//中软无效工时收入
            	if(row.chsoftiInvalid!=null){
            		temp=floatObj.add(temp, row.chsoftiInvalid, 2, 'add');
            	}
            	//加班费工时收入
            	if(row.chsoftiInfOt!=null){
            		temp=floatObj.add(temp, row.chsoftiInfOt, 2, 'add');
            	}
            	//调休工时收入
            	if(row.chsoftiInfPt!=null){
            		temp=floatObj.add(temp, row.chsoftiInfPt, 2, 'add');
            	}
            	//调整上月工时收入
            	if(row.chsoftiInfAd!=null){
            		temp=floatObj.add(temp, row.chsoftiInfAd, 2, 'add');
            	}
            	//差旅收入
            	if(row.chsoftiInfTravel!=null){
            		temp=floatObj.add(temp, row.chsoftiInfTravel, 2, 'add');
            	}
            	//付费设备收入
            	if(row.chsoftiInfEquipment!=null){
            		temp=floatObj.add(temp, row.chsoftiInfEquipment, 2, 'add');
            	}
            	//分包收入
            	if(row.chsoftiInfSub!=null){
            		temp=floatObj.add(temp, row.chsoftiInfSub, 2, 'add');
            	}
            	//计算后的值赋给字段
            	row.chsoftiInfTotal=temp.toFixed(2);;
            	return "<font color='green' family='黑体'><strong>"+temp.toFixed(2);+"</strong></font>";
            	
            }
        },{
            field: 'chsoftiInfRmbtotal',//月收入合计(人民币)=月收入合计(原币种)*汇率
            title: 'InfRmbTotal',
            sortable: true,
            formatter: function (value, row, index) {  
            	var temp = 0;
            	if(row.exRate!=null){
            		temp = floatObj.multiply(row.chsoftiInfTotal, row.exRate, 2, 'multiply');
            	}
            	//计算后的值赋给字段
            	row.chsoftiInfRmbtotal=temp.toFixed(2);;
            	return "<font color='green' family='黑体'><strong>"+temp.toFixed(2);+"</strong></font>";
            	
            }
        },{
            field: 'chsoftiInfCurrent',//当月有效收入=(月收入合计原币种-无效工时收入)*汇率
            title: 'InfCurrent',
            sortable: true,
            formatter: function (value, row, index) {  
            	if(row.chsoftiInfTotal==null){
            		return "<font color='green' family='黑体'><strong>0</strong></font>";
            	}
            	var res = floatObj.subtract(row.chsoftiInfTotal, row.chsoftiInvalid, 2, 'subtract');
            	if(row.exRate!=null){
            		res2 = floatObj.multiply(res, row.exRate, 2, 'multiply');
            	}
            	//计算后的值赋给字段
            	row.chsoftiInfCurrent=res2.toFixed(2);;
            	return "<font color='green' family='黑体'><strong>"+res2.toFixed(2);+"</strong></font>";
            }
        },
        {
            field: 'chsoftiEffectiveNr',//有效NR=当月有效收入/1.06
            title: 'EffectiveNr',
            sortable: true,
            formatter: function (value, row, index) {  
            	if(row.chsoftiInfCurrent==null){
            		return "<font color='green' family='黑体'><strong>0</strong></font>";
            	}
            	var res = floatObj.divide(row.chsoftiInfCurrent, 1.06, 2, 'divide');
            	//计算后的值赋给字段
            	row.chsoftiEffectiveNr=res.toFixed(2);
            	return "<font color='green' family='黑体'><strong>"+res.toFixed(2);+"</strong></font>";
            }
        },
        {
            field: 'chsoftiEffectiveSt',//当月有效人力=中软实际工时/中软月标准工时
            title: 'EffectiveSt',
            sortable: true,
            formatter: function (value, row, index) {  
            	if(row.chsoftiAwHours==null){
            		return "<font color='green' family='黑体'><strong>0</strong></font>";
            	}
            	var res = floatObj.divide(row.chsoftiAwHours, row.chsoftiMskHours, 2, 'divide');
            	//计算后的值赋给字段
            	row.chsoftiEffectiveSt=res.toFixed(2);;
            	return "<font color='green' family='黑体'><strong>"+res.toFixed(2);+"</strong></font>";
            }
        },
        {
            field: 'chsoftiInvalidSt',//当月无效人力=中软无效工时/中软月标准工时
            title: 'InvalidSt',
            sortable: true,
            formatter: function (value, row, index) {  
            	if(row.chsoftiIwHours==null){
            		return "<font color='green' family='黑体'><strong>0</strong></font>";
            	}
            	var res = floatObj.divide(row.chsoftiIwHours, row.chsoftiMskHours, 2, 'divide');
            	//计算后的值赋给字段
            	row.chsoftiInvalidSt=res.toFixed(2);;
            	return "<font color='green' family='黑体'><strong>"+res.toFixed(2);+"</strong></font>";
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
        	console.log(row);
        	//获取当前页数据
        	var currentData = $('#OfflineOperList').bootstrapTable('getData');
        	for(var i=0;i<currentData.length;i++){
        		//比对数据获取行号
        		if(currentData[i]==row){
        			//中软实际工时有变动
        			if(field=="chsoftiAwHours"){
        				//校验中软实际工时不能大于标准工时
        				if(row.chsoftiAwHours>row.chsoftiMskHours){
        					alert("实际工时不能大于标准工时");
        					//清空实际工时单元格
        					upCell(i,"chsoftiAwHours",0,row.billRate);
        					return;
        				}
        				upCell(i,"chsoftiIfaw",row.chsoftiIfaw,row.billRate);
        			}
        			//中软无效工时有变动
        			if(field=="chsoftiIwHours"){
        				upCell(i,"chsoftiInvalid",row.chsoftiInvalid,row.billRate);
        			}
        			//中软加班费工时有变动
        			if(field="chsoftiOtHours"){
        				upCell(i,"chsoftiInfOt",row.chsoftiInfOt,row.billRate);
        			}
        			//中软调休工时有变动
        			if(field="chsoftiToHours"){
        				upCell(i,"chsoftiInfPt",row.chsoftiInfPt,row.billRate);
        			}
        			//中软调整上月工时有变动
        			if(field="chsoftiApwHours"){
        				upCell(i,"chsoftiInfAd",row.chsoftiInfAd,row.billRate);
        			}
        			
        		}
        	}
            $.ajax({
                type: "POST",
                url: path+"/service/offlineOper/save",
                contentType: "application/json;charset=utf-8",
                data: JSON.stringify(row),
                dataType: 'JSON',
                success: function (data, status) {
                    if (data == "0") {
                    	var data = $('#OfflineOperList').bootstrapTable('getData');
                    	loadOfflineSummary(data);
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
        	var data = $('#OfflineOperList').bootstrapTable('getData');
        	loadOfflineSummary(data);
        },
        onLoadError: function () {
        	
        },
        onDblClickRow: function (row, $element) {
           
        }
        
    });
}

function search(){
	//获取查询条件
	var staffName = $("#staffName").val();
	var ehr = $("#ehr").val();
	//中软项目名称
	var projectName = $("#projectName").val();
	//中软项目编号
	var proNumber = $("#projectNumber").val();
	//中软部门
	var csdeptid = $("#csSubDept").val();
	var queryParams = { 
		query: {  
		   staffName:staffName,
		   eHr:ehr,
		   projectName:projectName,
		   proNumber:proNumber,
		   csdeptid:csdeptid
        }
    }  
	//刷新表格  
    $('#OfflineOperList').bootstrapTable('refresh',queryParams);  
}

//更新指定单元格数据
function upCell(index,field,value,billRate){
	/*if(billRate==null){
		alert("此员工没有单价，无法计算");
		return;
	}*/
	var cellValue = {
            index : index,  //更新列所在行的索引
            field : field, //要更新列的field
            value : value //要更新列的数据
        }//更新表格数据        
    $('#OfflineOperList').bootstrapTable("updateCell",cellValue);
}

//导出
function exportData(){
	uri = path+'/service/offlineOper/export',
	window.location.href=uri;
}
























/************************************
 * 
 * 
 * 处理js 运算精度问题
 * 
 * 
 ************************************/
//加法   
Number.prototype.add = function(arg){   
    var r1,r2,m;   
    try{r1=this.toString().split(".")[1].length}catch(e){r1=0}   
    try{r2=arg.toString().split(".")[1].length}catch(e){r2=0}   
    m=Math.pow(10,Math.max(r1,r2))   
    return (this*m+arg*m)/m   
}  
//减法  
Number.prototype.sub = function (arg){   
    return this.add(-arg);   
}   
//乘法   
Number.prototype.mul = function (arg){   
    var m=0,s1=this.toString(),s2=arg.toString();   
    try{m+=s1.split(".")[1].length}catch(e){}   
    try{m+=s2.split(".")[1].length}catch(e){}   
    return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)   
}   
//除法   
Number.prototype.div = function (arg){   
    var t1=0,t2=0,r1,r2;   
    try{t1=this.toString().split(".")[1].length}catch(e){}   
    try{t2=arg.toString().split(".")[1].length}catch(e){}   
    with(Math){   
        r1=Number(this.toString().replace(".",""))   
        r2=Number(arg.toString().replace(".",""))   
        return (r1/r2)*pow(10,t2-t1);   
    }   
}


/*  add / subtract / multiply /divide
*
* ** explame **
*  0.1 + 0.2 == 0.30000000000000004 （多了 0.00000000000004）
*  0.2 + 0.4 == 0.6000000000000001  （多了 0.0000000000001）
*  19.9 * 100 == 1989.9999999999998 （少了 0.0000000000002）
*
* floatObj.add(0.1, 0.2) &gt;&gt; 0.3
* floatObj.multiply(19.9, 100) &gt;&gt; 1990
*
*/
var floatObj = function() {

   /*
    * 判断obj是否为一个整数
    */
   function isInteger(obj) {
       return Math.floor(obj) === obj
   }

   /*
    * 将一个浮点数转成整数，返回整数和倍数。如 3.14 &gt;&gt; 314，倍数是 100
    * @param floatNum {number} 小数
    * @return {object}
    *   {times:100, num: 314}
    */
   function toInteger(floatNum) {
       var ret = {times: 1, num: 0}
       if (isInteger(floatNum)) {
           ret.num = floatNum
           return ret
       }
       var strfi  = floatNum + ''
       var dotPos = strfi.indexOf('.')
       var len    = strfi.substr(dotPos+1).length
       var times  = Math.pow(10, len)
       var intNum = parseInt(floatNum * times + 0.5, 10)
       ret.times  = times
       ret.num    = intNum
       return ret
   }

   /*
    * 核心方法，实现加减乘除运算，确保不丢失精度
    * 思路：把小数放大为整数（乘），进行算术运算，再缩小为小数（除）
    *
    * @param a {number} 运算数1
    * @param b {number} 运算数2
    * @param digits {number} 精度，保留的小数点数，比如 2, 即保留为两位小数
    * @param op {string} 运算类型，有加减乘除（add/subtract/multiply/divide）
    *
    */
   function operation(a, b, digits, op) {
       var o1 = toInteger(a)
       var o2 = toInteger(b)
       var n1 = o1.num
       var n2 = o2.num
       var t1 = o1.times
       var t2 = o2.times
       var max = t1 > t2 ? t1 : t2
       var result = null
       switch (op) {
           case 'add':
               if (t1 === t2) { // 两个小数位数相同
                   result = n1 + n2
               } else if (t1 > t2) { // o1 小数位 大于 o2
                   result = n1 + n2 * (t1 / t2)
               } else { // o1 小数位 小于 o2
                   result = n1 * (t2 / t1) + n2
               }
               return result / max
           case 'subtract':
               if (t1 === t2) {
                   result = n1 - n2
               } else if (t1 > t2) {
                   result = n1 - n2 * (t1 / t2)
               } else {
                   result = n1 * (t2 / t1) - n2
               }
               return result / max
           case 'multiply':
               result = (n1 * n2) / (t1 * t2)
               return result
           case 'divide':
               result = (n1 / n2) * (t2 / t1)
               return result
       }
   }

   // 加减乘除的四个接口
   function add(a, b, digits) {
       return operation(a, b, digits, 'add')
   }
   function subtract(a, b, digits) {
       return operation(a, b, digits, 'subtract')
   }
   function multiply(a, b, digits) {
       return operation(a, b, digits, 'multiply')
   }
   function divide(a, b, digits) {
       return operation(a, b, digits, 'divide')
   }

   // exports
   return {
       add: add,
       subtract: subtract,
       multiply: multiply,
       divide: divide
   }
}();



