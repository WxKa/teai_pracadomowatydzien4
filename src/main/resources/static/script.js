
$(document).ready(function() {
	$("tbody.grid").on("click", "tr", function(e) {
		$(this)
			.toggleClass("selected")
			.siblings(".selected")
				.removeClass("selected");

		var td0 = this.cells[0];
		$('#id1').val( td0.innerHTML );
		$('#id2').val( td0.innerHTML );
//		$('#id3').val( td0.innerHTML );

		var td1 = this.cells[1];
		$('#mark1').val( td1.innerHTML );
		$('#mark2').val( td1.innerHTML );
//		$('#mark3').val( td1.innerHTML );

		var td2 = this.cells[2];
		$('#model1').val( td2.innerHTML );
		$('#model2').val( td2.innerHTML );
//		$('#model3').val( td2.innerHTML );

		var td3 = this.cells[3];
		$('#color1').val( td3.innerHTML );
		$('#color2').val( td3.innerHTML );
//		$('#color3').val( td3.innerHTML );
	});
});
