<!DOCTYPE html>
<html>
    <head>
        <link href="style/style.css"/>
        <script src="js/jquery.js"></script>
        <script src="js/jquery-ui-1.10.2.custom.min.js"></script>

        <script>
            $(document).ready(function() {
                $("#sortable").sortable();
            });
        </script>
    </head>
    <body>
        <div class="reordenando">
            <ul id="sortable">
                <li>ThemeForest</li>
                <li>GraphicRiver</li>
                <li>ActiveDen</li>
                <li>VideoHive</li>
                <li>3DOcean</li>
            </ul>   
        </div>
    </body>
</html>