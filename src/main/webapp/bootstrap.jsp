<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.bundle.min.js" integrity="sha384-u/bQvRA/1bobcXlcEYpsEdFVK/vJs3+T+nXLsBYJthmdBuavHvAW6UsmqO2Gd/F9" crossorigin="anonymous"></script>
<style>
    // In Bootstrap's _variables.scss file...
    $theme-colors: () !default;
    $theme-colors: map-merge((
    primary: blue,
    secondary: gray,
    success: red,
    info: cyan,
    warning: yellow,
    danger: red
    ), $theme-colors);

    // In a custom variables file, before or after.
    $theme-colors: (
    success: rgba(,255,0,.5)
    );

    .test {
        background-color: map-get($theme-colors, "primary");
    }


</style>

