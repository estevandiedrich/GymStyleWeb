<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/jqueryImage.js"></script>
<script type="text/javascript" src="js/interface.js"></script>
<style type="text/css" media="screen">
    body
    {
        font-family:Arial, Helvetica, sans-serif;
        font-size: 11px;
        color: #666666;
        margin: 0;
        padding: 0;
    }
    a
    {
        text-decoration: none;
    }
    img
    {
        border: none;
    }
    #ImageBoxOverlay
    {
        background-color: #000;
    }
    #ImageBoxCaption
    {
        background-color: #F4F4EC;
    }
    #ImageBoxContainer
    {
        width: 250px;
        height: 250px;
        background-color: #F4F4EC;
    }
    #ImageBoxCaptionText
    {
        font-weight: bold;
        padding-bottom: 5px;
        font-size: 13px;
        color: #000;
    }
    #ImageBoxCaptionImages
    {
        margin: 0;
    }
    #ImageBoxNextImage
    {
/*        background-image: url(images/spacer.gif);*/
        background-color: transparent;
    }
    #ImageBoxPrevImage
    {
/*        background-image: url(images/spacer.gif);*/
        background-color: transparent;
    }
    #ImageBoxNextImage:hover
    {
/*        background-image: url(images/next_image.jpg);*/
        background-repeat:	no-repeat;
        background-position: right top;
    }
    #ImageBoxPrevImage:hover
    {
/*        background-image: url(images/prev_image.jpg);*/
        background-repeat:	no-repeat;
        background-position: left bottom;
    }
    h2,h4,p{
        padding-left: 20px;
    }

    #buttonCamera{
        background-image: url(images/cam.png);
    }

</style>
<p>
    <a href="images/camTopo.png" title="Salve o arquivo com extensão (.jpg)" rel="imagebox-bw">
        <img src="images/cam.png" title="Abrir Câmera"/>
    </a>
</p>
<script type="text/javascript">
    $(document).ready(
    function()
    {
        $.ImageBox.init(
        {
            loaderSRC: 'images/loading.gif',
            closeHTML: '<img src="images/close.jpg" />'
        }
    );
    }
);
</script>
<div id="ImageBoxOverlay" style="position: absolute; top: 0px; left: 0px; height: 624px; width: 1366px; opacity: 0; 
     overflow-x: visible; overflow-y: visible; display: none; ">
</div>
<div id="ImageBoxOuterContainer" style="position: absolute; left: 0px; text-align: center; background-color: transparent;
     width: 1366px; top: 41.6px; opacity: 1; overflow-x: hidden; overflow-y: hidden; display: none; ">
    <div id="ImageBoxContainer" style="text-align: left; margin-top: 0px; margin-right: auto; margin-bottom: 0px; margin-left: auto; 
         top: 0px; left: 0px; z-index: 2; position: relative; height: 418px; width: 320px; display: block; overflow-x: hidden; 
         overflow-y: hidden; ">
        <img src="images/loading.gif" id="ImageBoxLoader" style="position: absolute; left: 10px; top: 10px; display: none; "/>
    </div>
    <div id="ImageBoxCaption" style="position: relative; text-align: left; margin-top: 0px; margin-right: auto; margin-bottom: 0px; margin-left: auto; z-index: 1; width: 320px; top: -1px; display: block; overflow-x: visible; overflow-y: visible; visibility: hidden; ">
        <div id="ImageBoxCaptionText" style="padding-left: 10px; "></div>
        <%@include  file="camera.jsp" %>
        <a id="ImageBoxClose" href="usuarioCreate.definir.do" style="position: absolute; right: 10px; top: 0px; ">
            <img src="images/close.jpg">
        </a>
        <div id="" style="padding-left: 10px; padding-bottom: 10px; ">Selecione sair para voltar a aplicação</div>
    </div>
</div>