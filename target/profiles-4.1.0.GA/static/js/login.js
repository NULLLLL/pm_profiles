/**
 * 
 */
var login = function(){};
$(function(){
});
login.formSub = function(){
	var b = new Base64();  
	var password = b.encode($('#password').val());
	$('#pwd').val(password);
	$('#loginForm').submit();
}