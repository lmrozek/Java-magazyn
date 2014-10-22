$(document).ready(function() {
    $("#search")
            .keyup(function() {
                $(".results")
                        .hide()
                        .filter(function() {
                            return $(this).children(".resultsName").text().toLowerCase().indexOf($("#search").val()) >= 0;
                        })
                        .show();
            });
});

//    $("#search")
//	.on("input", function () {
//		$("#results li")
//			.hide()
//			.filter(function () {
//				return $(this).text().indexOf($("#search").val()) >= 0;
//			})
//			.show();
//	});

//< script >   //alternatywa do lini 2. (ze strony: http://api.jquery.com/val/)
//        $("input")
//        .keyup(function() {
//            var value = $(this).val();
//            $("p").text(value);
//        })
//        .keyup();
//< /script>