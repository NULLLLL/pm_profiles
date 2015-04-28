/**
 * 
 */
var ctx = '/profiles';
var profilesList = function() {
};

$(function() {
	
	profilesList.initDataTable();
	$('#searchProfilesData').click(function() {
		profilesList.searchTable();
	});
	$(window).resize(function() {
		$('#ProfilestableId').bootstrapTable('resetView');
	});
	$('#export').click(function() {
		profilesList.exportExcel();
	});
});

profilesList.initDataTable = function() {
	$('#ProfilestableId').bootstrapTable({
		method : 'get',
		url : ctx + '/listTable',
		cache : false,
		height : 550,
		queryParams : function(params) {
			return {
				params : sysutil.findFormData('#searchDiv :input')
			};
		},
		striped : true,
		pagination : true,
		pageNumber : 1,
		pageSize : 20,
		pageList : [ 10, 25, 50, 100 ],
		search : false,
		showColumns : true,
		showRefresh : false,
		minimumCountColumns : 2,
		smartDisplay : true,
		clickToSelect : false,
		columns : [ {field : 'id',checkbox : true}, 
		            {field : 'p_number',title : 'p_number',sortable : true}, 
		            {field : 'name',title : 'name',sortable : true}, 
		            {field : 'lower_size',title : 'lower_size',sortable : true}, 
		            {field : 'upper_size',title : 'upper_size',sortable : true}, 
		            {field : 'weight_per',title : 'weight_per',sortable : true},
		            {field : 'uncertaint',title : 'uncertaint',sortable : true},
		            {field : 'symbol',title : 'symbol',sortable : true},
		            {field : 'document',title : 'document',sortable : true}
		            		]
	});
};

profilesList.searchTable = function() {
	$('#ProfilestableId').bootstrapTable('refresh');
};

profilesList.idformatter = function(data, record) {
	return '<input type="checkbox" value="' + record.id + '"/>';
};

var warning = '<div id="messageDiv" class="alert alert-success" style="display: none;">'
		+ '<button data-dismiss="alert" class="close">×</button>'
		+ '<span id="messagespan"></span>' + '</div>';


profilesList.exportExcel = function(){
	location.href = ctx + '/admin/export';
};
