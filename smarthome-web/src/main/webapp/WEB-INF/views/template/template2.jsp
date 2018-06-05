<!DOCTYPE HTML> 
<html> 
    <head> 
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"> 
        <title>title</title>
        <script type="text/javascript" src="../js/jquery-1.9.1.min.js"></script>
        <style type="text/css">
            body{background-color: #f5f6f7;}
            .imgHolder{border: 2px dotted #bbb;color:#bbb;width: 120px;height: 140px;line-height: 140px;border-radius: 8px;cursor: pointer;text-align: center;position: relative;background-color: #fff;}
            .imgHolder:hover{border-color: #999;color: #999;}
            .imgHolder .add{font-size: 50px;}
            .imgHolder img{width: 100%;height: 100%;}
            .fileUploadBtn{position: absolute;top: 0px;left: 0px;width: 100%;height: 100%;cursor: pointer;outline: none;border:0 none;z-index: 1;opacity: 0;filter:alpha(opacity:1);}
            .loading{position: absolute;top: 0px;left: 0px;width: 100%;height: 100%;z-index: 2;display: none;
                background:url("data:image/gif;base64,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") center center no-repeat;
            }
            .loading span{font-size: 12px;display: block;margin-top: 30px;}
        </style>
    </head> 
    <body>
        <div class="imgHolder">
            <span class="add">+</span>
            <form action="upload.php" id="uploadFrom" method="post" target="uploadHolderFrame" enctype="multipart/form-data">
                <input type="file" name="file" class="fileUploadBtn" accept="image/*"/>
            </form>
            <div class="loading"><span>正在上传...</span></div>
        </div>
        <iframe id="uploadHolderFrame" name="uploadHolderFrame" style="position:absolute;top:-100px;left:-100px;width:0px;height:0px;"></iframe>
        <script type="text/javascript">
            jQuery(document).ready(function() {
                $('input[name="file"]').bind('change',function(){
                    $('#uploadFrom').submit();
                    $('.loading').show();
                });
                $('#uploadHolderFrame').bind('load',function(){
                    var bodyContent = $(window.frames['uploadHolderFrame'].document.body).html();
                    $('body').append('<div>'+bodyContent+'</div>')
                    $('<img style="display:none;" src="'+bodyContent+'"/>').appendTo('.imgHolder').bind('load',function(){
                        $('.imgHolder').empty().append($(this));
                        $(this).show();
                    })
                });
            });
        </script>
    </body> 
</html>