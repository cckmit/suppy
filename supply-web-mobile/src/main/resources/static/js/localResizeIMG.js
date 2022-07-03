$.fn.localResizeIMG = function (obj) {
    this.on('change', function (e) {
        var file = this.files[0];
        var URL = window.URL || window.webkitURL;
        var blob = URL.createObjectURL(file);
        if ($.isFunction(obj.before)) {
            obj.before(this, blob, file)
        }
        ;
        _create(blob, file);
        this.value = '';
    });

    function _create(blob, file) {
        var Orientation = '';
        var img = new Image();
        img.src = blob;
        EXIF.getData(file, function () {

            EXIF.getAllTags(this);

            Orientation = EXIF.getTag(this, 'Orientation');//这个Orientation 就是我们判断需不需要旋转的值了，有1、3、6、8
        });

        img.onload = function () {
            var that = this;
            var w = that.width,
                h = that.height,
                scale = w / h;
            w = obj.width || w;
            h = w / scale;
            var canvas = document.createElement('canvas');
            var ctx = canvas.getContext('2d');
            $(canvas).attr({width: w, height: h});
            ctx.drawImage(that, 0, 0, w, h);

            var base64 = canvas.toDataURL('image/jpeg', obj.quality || 0.8);
            if (navigator.userAgent.match(/iphone/i)) {
                var mpImg = new MegaPixImage(img);
                mpImg.render(canvas, {maxWidth: w, maxHeight: h, quality: obj.quality || 0.8});
                if (Orientation == 6) {
                    Orientation = ''
                    rotateImg(this, 'left', canvas);
                }
                base64 = canvas.toDataURL('image/jpeg', obj.quality || 0.8);

            }
            if (navigator.userAgent.match(/Android/i)) {
                var encoder = new JPEGEncoder();
                base64 = encoder.encode(ctx.getImageData(0, 0, w, h), obj.quality * 100 || 80);
            }
            var result = {base64: base64, clearBase64: base64.substr(base64.indexOf(',') + 1)};
            obj.success(result, file);
        };
    }
};

function rotateImg(img, direction, canvas) {
    //最小与最大旋转方向，图片旋转4次后回到原方向
    var min_step = 0;
    var max_step = 3;
    //var img = document.getElementById(pid);
    if (img == null) return;
    //img的高度和宽度不能在img元素隐藏后获取，否则会出错
    var height = img.height;
    var width = img.width;
    //var step = img.getAttribute('step');
    var step = 2;
    if (step == null) {
        step = min_step;
    }
    if (direction == 'right') {
        step++;
        //旋转到原位置，即超过最大值
        step > max_step && (step = min_step);
    } else {
        step--;
        step < min_step && (step = max_step);
    }
    var degree = step * 90 * Math.PI / 180;
    var ctx = canvas.getContext('2d');
    switch (step) {
        case 0:
            canvas.width = width;
            canvas.height = height;
            ctx.drawImage(img, 0, 0);
            break;
        case 1:
            canvas.width = height;
            canvas.height = width;
            ctx.rotate(degree);
            ctx.drawImage(img, 0, -height);
            break;
        case 2:
            canvas.width = width;
            canvas.height = height;
            ctx.rotate(degree);
            ctx.drawImage(img, -width, -height);
            break;
        case 3:
            canvas.width = height;
            canvas.height = width;
            ctx.rotate(degree);
            ctx.drawImage(img, -width, 0);
            break;
    }
}