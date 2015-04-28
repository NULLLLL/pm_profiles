/**
 * Define Sysutil class extends object
 */

String.prototype.replaceAll = function(regexp,replaceValue){   
	return this.replace(new RegExp(regexp,"gm"),replaceValue);   
};

//all
//全选
function checkAll(checkAll, name){
	var o=document.getElementsByName(name);
	if(checkAll){
		for(i=0;i<o.length;i++)
			o[i].checked=true;
	} else {
		 for(i=0;i<o.length;i++){
			 o[i].checked=false;
		 }
	}
}

//搜索显示
function searchbaron(id){
	var bar=document.getElementById(id);
	if(bar.style.display == "none")
		bar.style.display="block";
	else 
		bar.style.display="none";	
}

var Sysutil = Class(object, //extends object
{
	gridWithSelector : '#row', 
	widthPercent:0.98,
	selectorTpl : '<option value="{value}">{text}</option>',
	selectorTplValue : '{value}',
	selectorTplText : '{text}',
	Create : function()
	{ 
		
	},
	checkMobile : function(mobile){ /* 正则表达式验证手机号 */
		if(!(/^1[3|4|5|8][0-9]\d{4,8}$/.test(mobile)))
			return false; 
	},
	isNumber : function(val){
		if ("" == val)
			return false;
		return !(/\D/.test(val));
	},
	logoutConfirm : function(){
		if(confirm('是否退出系统？'))
			window.location.href = contex+'/logout';
	},
	initSelectMenu : function(moduleId,lefbarId,lefbarmenuId){
		$('#'+moduleId).addClass("on").siblings().removeClass("on");
		$('#'+moduleId+' a ').css({'color':'#666666'}).siblings().css({'color':'#FFFFFF'});
		$('#'+lefbarId).show().siblings().hide();
		$('#'+lefbarmenuId).addClass("selected");
		$('#'+lefbarmenuId).siblings().removeClass("selected");
		
	},
	initActiveMenu : function(moduleId) {
		$('#'+moduleId).addClass("active").siblings().removeClass("active");
		
	},
	
	showDiv : function(objId){ /* bootstrap base 弹出层方法 */
		var obj = document.getElementById(objId);
		var obj2 = $('#'+objId);
		var finalHeight = obj2.height()/2;
		var finalWidth = obj2.width()/2;
		obj.style.marginTop = -finalHeight;
		obj.style.marginLeft = -finalWidth;
		$('#' + objId).modal('show');
	},
	hideDiv : function(objId){
		$('#' + objId).modal('hide');
	},
	getTempArray : function(checkName){ /* 获取单选按钮的值 */
		var tempArr = new Array();
		var obj = document.getElementsByName(checkName);
		for ( var i = 0; i < obj.length; i++) 
				if(obj[i].checked == true)
					tempArr.push(obj[i].value);
		return tempArr;
	},
	getCurTime : function(){ /* 获取当前时间yyyy-mm-dd hh:mm:ss */
		var curDate = new Date();
		var newMonth;
		var newDate;
		var newHour;
		var newMin;
		if((curDate.getMonth()+1) < 10)
			newMonth = '0'+(curDate.getMonth()+1);
		else
			newMonth = curDate.getMonth()+1;
		if(curDate.getDate() < 10)
			newDate = '0'+curDate.getDate();
		else
			newDate = curDate.getDate();
		if(curDate.getHours() < 10)
			newHour = '0'+curDate.getHours();
		else
			newHour = curDate.getHours();
		if(curDate.getMinutes() < 10)
			newMin = '0'+curDate.getMinutes();
		else
			newMin = curDate.getMinutes();
		var curDateTime = curDate.getFullYear()+'-'+newMonth+'-'+newDate + ' '+newHour+':'+newMin + ":00";
		return curDateTime;
	},
	/**
	 * 获取from 表单数据
	 * @param fromSelector - eg."#fromid :input"
	 * @returns {___anonymous1824_1826}
	 */
	findFormDataObject : function(fromSelector){
		var formData = { };
		var input = $(fromSelector);
		for (var i = 0; i < input.length; i++){
			if (input[i].name != undefined && input[i].name != ""){
				if (input[i].type == 'radio'){
					if (input[i].checked)
						formData[input[i].name] = input[i].value;
				} else {
					if (input[i].type == 'checkbox'){
						if (input[i].checked){
							var fd  = formData[input[i].name];
							if (fd == undefined ){
								formData[input[i].name] = input[i].value;
							}else{
								formData[input[i].name] = fd + "," + input[i].value;
							}
						}
					} else{
						if(input[i].placeholder == input[i].value || $(input[i]).attr('placeholder') == input[i].value)
							formData[input[i].name] = '';
						else
							formData[input[i].name] = input[i].value;
					}
				} 
			}
		}
		return formData;
	},
	/**
	 * 序列化：将对象转成可传输字符传
	 * @param fromSelector
	 * @returns stringify
	 */
	findFormData : function(fromSelector){
		return JSON.stringify(this.findFormDataObject(fromSelector));
	},
	/**
	 * 序列化：将对象转成可传输字符传
	 * @param objectData
	 * @returns
	 */
	josnStringify : function(objectData){
		return JSON.stringify(objectData);
	},
	/**
	 * 反填form 表单
	 * @param formSelector edit form input selector.
	 * @param rowData 一条json数据
	 */
	fillForm : function(formSelector,rowData){
		var input = $(formSelector);
		for (var i= 0; i < input.length; i++){
			if (input[i].name != undefined && input[i].name != ""){
				input[i].value = rowData[input[i].name];
			}
		}
	},
	/**
	 * 表单查询
	 * @param gridId 	 	grid id 			- '#listid'
	 * @param fromSelector  form data selector 	- '#formid :input'
	 * @param name			controller key		- 'params'
	 */
	searchListWithParams : function(gridId,fromSelector,name){
		var formData = this.findFormData(fromSelector);
		var postData = $(gridId).jqGrid("getGridParam", "postData"); 
		postData[name] = formData;
		$(gridId).jqGrid('setGridParam',{ search : true,datatype:'json' }).trigger("reloadGrid", [{page:1}]);
	},
	/**
	 * 传入json data reload gridlist.
	 * @param gridId	grid id .
	 * @param formData 	json data.
	 * @param name		paramKey.
	 */
	reloadListWithParams : function(gridId,formData,name){
		var postData = $(gridId).jqGrid("getGridParam", "postData"); 
		if (formData != null || name != null)
			postData[name] = JSON.stringify(formData);
		$(gridId).jqGrid('setGridParam',{ search : true }).trigger("reloadGrid", [{page:1}]);
	},
	/**
	 * 清空grid数据
	 * @param gridId	
	 * @param caption	optional
	 */
	clearGrid : function(gridId,caption){
		if (caption)
			$(gridId).jqGrid('setCaption',caption).jqGrid('clearGridData');
		else
			$(gridId).jqGrid('clearGridData');
	},
	
	initDateTimePicker: function(inputId,date){
		return $('#'+inputId).datetimepicker({
			startDate:date?date:null,
			pickTime: false,
			format: 'yyyy-MM-dd',
		    language: 'zh-CN' 
		});
	},
	/**
	 * 
	 * @param ajaxOptions{
	 * 		url		:	''							//请求地址
	 * 		mtype	:	'POST'						//method type : get or post(defualt)
	 * 		data	:	{}							//post data params请求参数json data type.
	 * 		success :	function(data,st, xhr){}	//callback function sucess.
	 * 		error	:	function(xhr,st,err){}		//callback function error.
	 * }
	 */
	commAjax : function(ajaxOptions){
		$.ajax({
			url:ctx+ajaxOptions.url,
			type:ajaxOptions.mtype == undefined ? 'POST' : ajaxOptions.mtype,
			dataType: ajaxOptions.dataType == undefined ? 'json' : ajaxOptions.dataType ,
			data: ajaxOptions.postData == undefined ? {} : ajaxOptions.postData ,
			async:ajaxOptions.async == undefined ? true : ajaxOptions.async,
			success:function(data,st, xhr) {
				if ($.isFunction(ajaxOptions.success)) {
					ajaxOptions.success.call(ajaxOptions,data, st, xhr);
				} else{
					
				}
				xhr=null;
			},
			error:function(xhr,st,err){
				if ($.isFunction(ajaxOptions.error)) {
					ajaxOptions.error.call(ajaxOptions,xhr,st,err);
				} else{
					
				}
				xhr=null;
			}
		});
	},
	/**
	 * 新开页面
	 * @param url
	 */
	openWindow : function(url){
		window.open(url,'_blank',"location=yes,menubar=no,width="+ (screen.availWidth - 0) +',height='+ (screen.availHeight-0) +",resizable=yes,scrollbars=yes,status=no,toolbar=no" );
	},
	/**
	 * 下载请求
	 * @param url
	 */
	down : function(url){
		var downloadform = $('#downloadform');
		if (downloadform.length <= 0)
			$('body').append('<form id="downloadform" action="" method="post" target="downloadiframe" ></form><iframe id="downloadiframe" name="downloadiframe" style="display:none;"></iframe>');
		$('#downloadform').attr('action',url);
		document.getElementById('downloadform').submit();
//		window.open(url,'_blank',"location=no,menubar=no,width=1,height=1,resizable=no,scrollbars=no,status=no,toolbar=no" );
	},
	/**
	 * color : rgb(r,g,b) to hex
	 * @param rgb
	 * @returns
	 */
	RGBToHex : function (rgb) {
		var hex = [
			rgb.r.toString(16),
			rgb.g.toString(16),
			rgb.b.toString(16)
		];
		$.each(hex, function (nr, val) {
			if (val.length == 1) {
				hex[nr] = '0' + val;
			}
		});
		return hex.join('');
	},
	/**
	 * 导出CSV
	 * @param formSelector 查询条件的选择器
	 * @param bizKey 业务标识
	 * @param formData 查询条件json字符串
	 */
	exportCSV : function(formSelector, bizKey, formData){
		if (!confirm('您确定导出符合搜索条件的数据吗？'))
			return;
		var params = "{}";
		if (formSelector != null && formSelector != '')
			params = sysutil.findFormData(formSelector);
		if (formData != undefined && formData != null && formData != '')
			params = formData;
		var url = ctx+"/export/downloadCSV?params="+params+"&bizKey="+bizKey;
//		console.log(url);
		url = encodeURI( encodeURI( url ) );
		this.down(url);
	},
	/**
	 * 初始化下拉框
	 * @param targetSelector select input selector ,eg.#selid or .selclass
	 * @param textArray		option's text array
	 * @param defaultText	default text
	 */
	initSelect : function(targetSelector, textArray, defaultText){
		var opts = this.selectorTpl.replaceAll(this.selectorTplValue,'').replaceAll(this.selectorTplText, defaultText);
		for (var i = 0; i < textArray.length; i++){
			opts += this.selectorTpl.replaceAll(this.selectorTplValue,i).replaceAll(this.selectorTplText, textArray[i]);
		}
		$(targetSelector).html(opts);
	},
	/**
	 * 初始化下拉框
	 * @param targetSelector select input selector ,eg.#selid or .selclass
	 * @param textArray		option's [{text:'',value:''}]
	 * @param defaultText	default text
	 */
	initSelectArray : function(targetSelector, textArray, defaultText){
		var opts = this.selectorTpl.replaceAll(this.selectorTplValue,'').replaceAll(this.selectorTplText, defaultText);
		for (var i = 0; i < textArray.length; i++){
			opts += this.selectorTpl.replaceAll(this.selectorTplValue,textArray[i].value).replaceAll(this.selectorTplText, textArray[i].text);
		}
		$(targetSelector).html(opts);
	},
	/**
	 * 初始化下拉框,value为汉字
	 * @param targetSelector select input selector ,eg.#selid or .selclass
	 * @param textArray		option's [{text:'',value:''}]
	 * @param defaultText	default text
	 */
	initSelectWithTextArray : function(targetSelector, textArray, defaultText){
		var opts = this.selectorTpl.replaceAll(this.selectorTplValue,'').replaceAll(this.selectorTplText, defaultText);
		for (var i = 0; i < textArray.length; i++){
			opts += this.selectorTpl.replaceAll(this.selectorTplValue,textArray[i].text).replaceAll(this.selectorTplText, textArray[i].text);
		}
		$(targetSelector).html(opts);
	},
	/**
	 * 当浏览器窗口改变时，gridWith自适应
	 */
	getGridWith : function(containerSelector){
		if (undefined == containerSelector)
			return $(this.gridWithSelector).width() * this.widthPercent;
		else
			return $(containerSelector).width() * this.widthPercent * 0.94;
	} 
	
});