$(function() {
	plot = document.getElementById('plot');
	textresult = document.getElementById('textresult');

	var host;

	//console.log(document.location.hostname);
    /* Set server URIs */
    // 中文：设置服务器 URI（请求地址）。
    /*if (document.location.hostname !== 'localhost') {
        host = 'http://' + document.location.host;
    } else {
        host = 'http://localhost:4567';
    }

    console.log(host);*/

    host = 'http://localhost:4567';
    const history_server = host + '/history';
    const historytext_server = host + '/historytext';
    const hyponyms_server = host + '/hyponyms';
    const hypohist_server = host + '/hypohist';
//    const hypohisttext_server = host + '/hypohisttext';
    const commonancestors_server = host + '/ancestors';

    var ngordnetQueryType = "HYPONYMS";
    function get_params() {
        return {
            words: document.getElementById('words').value,
            startYear: document.getElementById('start').value,
            endYear: document.getElementById('end').value,
            k: document.getElementById('k').value,
            ngordnetQueryType: ngordnetQueryType
        }
    }

    $('#history').click(historyButton);
    $('#historytext').click(historyTextButton);
    $('#hyponyms').click(hyponymsButton);
    $('#hypohist').click(hypohistButton);
//    $('#hypohisttext').click(hypohistTextButton);
    $('#commonancestors').click(commonAncestorsButton);

    function historyButton() {
        $("#textresult").hide();
        $("#plot").show();

        var params = get_params();
        console.log(params);
        $.get({
            async: false,
            url: history_server,
            data: params,
            success: function(data) {
            	console.log(data)

                plot.src = 'data:image/png;base64,' + data;

            },
            error: function(data) {
                // 中文：请求出错。
            	console.log("error")
            	console.log(data);
            	plot.src = 'data:image/png;base64,' + data;
            },
            dataType: 'json'
        });
    }

    function historyTextButton() {
        // 中文：调用历史数据文本查询。
        console.log("history text call");
        $("#plot").hide();
        $("#textresult").show();

        var params = get_params();
        console.log(params);
        $.get({
            async: false,
            url: historytext_server,
            data: params,
            success: function(data) {
            	console.log(data)

                textresult.value = data;

            },
            error: function(data) {
                // 中文：请求出错。
            	console.log("error")
            	console.log(data);
            },
            dataType: 'json'
        });
    }

    function hyponymsButton() {
        // 中文：调用下位词查询。
        console.log("hyponyms call");
        $("#plot").hide();
        $("#textresult").show();
        ngordnetQueryType = "HYPONYMS";
        var params = get_params();
        console.log(params);
        $.get({
            async: false,
            url: hyponyms_server,
            data: params,
            success: function(data) {
                console.log(data)

                textresult.value = data;

            },
            error: function(data) {
                // 中文：请求出错。
                console.log("error")
                console.log(data);
            },
            dataType: 'json'
        });
    }


    function hypohistButton() {
        // 中文：调用下位词历史数据查询。
        console.log("hypohist call");
        $("#plot").show();
        $("#textresult").hide();

        var params = get_params();
        console.log(params);
        $.get({
            async: false,
            url: hypohist_server,
            data: params,
            success: function(data) {
            	console.log(data)

                plot.src = 'data:image/png;base64,' + data;

            },
            error: function(data) {
                // 中文：请求出错。
            	console.log("error")
            	console.log(data);
            	plot.src = 'data:image/png;base64,' + data;
            },
            dataType: 'json'
        });
    }

    function commonAncestorsButton() {
        // 中文：调用共同祖先查询。
        console.log("common ancestors call");
        $("#plot").hide();
        $("#textresult").show();
        ngordnetQueryType = "ANCESTORS";
        var params = get_params();
        console.log(params);
        $.get({
            async: false,
            url: hyponyms_server,
            data: params,
            success: function(data) {
                console.log(data)

                textresult.value = data;

            },
            error: function(data) {
                // 中文：请求出错。
                console.log("error")
                console.log(data);
            },
            dataType: 'json'
        });
    }



});