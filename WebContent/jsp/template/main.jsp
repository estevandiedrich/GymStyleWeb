<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="style/nivo-slider.css" rel="stylesheet" type="text/css" />
<link href="style/orman/orman.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.nivo.slider.js"></script>
<script>
    $(window).load(function(){
        $('#slider').nivoSlider();
    });
</script>
<mtw:form action="main.do" name="mainForm">
    <div class="title_bottom"></div>
    <div class="slider-wrapper theme-orman">
        <div id="slider" class="nivoSlider">
            <img src="images/academia1.png" alt="" />
            <img src="images/academia2.png" alt="" />
            <img src="images/academia3.png" alt="" />
            <img src="images/academia4.png" alt="" />
            <img src="images/academia5.png" alt="" />
        </div>
    </div>
</mtw:form>