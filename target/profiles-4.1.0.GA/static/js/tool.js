/**
 * 
 */

var tool = function() {
};

/**
 * 提示框
 */
tool.alert = function (message){
	var msg = $.globalMessenger().post({
		message: message,
		type: 'info',
		showCloseButton: true,
		hideAfter: 3
	});
};

tool.setInputFloatLabel = function(obj){
	var $this = $(obj);
	if ($this.hasClass("floating-label")) {
		var placeholder = $this.attr("placeholder");
		focused = setInterval(function() {clearInterval(focused);}, 10);
		$this.attr("placeholder", null).removeClass("floating-label");
		$this.after("<div class=floating-label>" + placeholder + "</div>");
	}
};
