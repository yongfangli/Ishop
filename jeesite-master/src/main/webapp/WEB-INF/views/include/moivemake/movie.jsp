<%@ page contentType="text/html;charset=UTF-8" %>
 <script src="${ctxStatic}/modules/DrawerJs-master/lib/jquery-1.10.1.min.js"></script>
    <script src="${ctxStatic}/modules/DrawerJs-master/lib/jquery-migrate-1.2.1.min.js"></script>
    <link rel="stylesheet" href="${ctxStatic}/modules/DrawerJs-master/assets/bootstrap.min.css"/>
    <link href="${ctxStatic}/modules/DrawerJs-master/assets/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${ctxStatic}/modules/DrawerJs-master/dist/drawerJs.css"/>
    <script src="${ctxStatic}/modules/DrawerJs-master/dist/drawerJs.standalone.js"></script>
    <script>
    var localization_cn =  {
  		  'Add Drawer': '添加画板',
  		  'Insert Drawer': '插入画板',
  		  'Insert': '插入',
  		  'Free drawing mode': '自由绘画模式',
  		  'SimpleWhiteEraser': '简白橡皮',
  		  'Eraser': '橡皮',
  		  'Delete this canvas': '删除该画板',
  		  'Are you sure want to delete this canvas?': '确定要删除该画板?',
  		  // canvas properties popup
  		  'Size (px)': '大小 (px)',
  		  'Position': '位置',
  		  'Inline': '在线',
  		  'Left': '左',
  		  'Center': '中心',
  		  'Right': '右',
  		  'Floating': '浮动',
  		  'Canvas properties': '画板属性',
  		  'Background': '背景',
  		  'transparent': '透明',
  		  'Cancel': '取消',
  		  'Save': '保存',

  		  // Fullscreen plugin
  		  'Enter fullscreen mode': '全名模式',
  		  'Exit fullscreen mode': '退出全屏模式',

  		  // shape context menu plugin
  		  'Bring forward': 'Bring forward',
  		  'Send backwards': 'Send backwards',
  		  'Bring to front': 'Bring to front',
  		  'Send to back': 'Send to back',
  		  'Duplicate': '重复',
  		  'Remove': '删除',
            //image
            'Insert an image':'插入图片',
            'Insert a background image':'插入背景图片',
            
  		  // brush size plugin
  		  'Size:': '大小:',

  		  // colorpicker plugin
  		  'Fill:': '填充:',
  		  'Transparent': '透明',

  		  // shape border plugin
  		  'Border:': '边框:',
  		  'None': '无',

  		  // arrow plugin
  		  'Draw an arrow': '箭头',
  		  'Draw a two-sided arrow': '双向箭头',
  		  'Lines and arrows': '线和箭头',

  		  // circle plugin
  		  'Draw a circle': '圆',

  		  // line plugin
  		  'Draw a line': '线',

  		  // rectangle plugin
  		  'Draw a rectangle': '长方形',

  		  // triangle plugin
  		  'Draw a triangle': '三角形',

  		  // polygon plugin
  		  'Draw a Polygon': '六边形',
  		  'Stop drawing a polygon': '停止画六边形',
  		  'Click to start a new line': '点击开始新的直线',
            'Border type:':'边框类型:',
  		  // text plugin
  		  'Draw a text': '文本',
  		  'Click to place a text': '该处设置文本',
  		  'Font:': '字体:',
            'Opacity:': '透明度:',
  		  // movable floating mode plugin
  		  'Move canvas': '移动画板',
            'Minimize canvas':'最小化',
            'Toggle fullscreen mode':'转换全屏模式',
            'Zoom in':'放大',
            'Zoom out':'缩小',
  		  // base shape
  		  'Click to start drawing a ': '点击开始画a '
  		}; 

  $(document).ready(function () {
      var drawerPlugins = [
          // Drawing tools
          'Pencil',
          'Eraser',
          'Text',
          'Line',
          'ArrowOneSide',
          'ArrowTwoSide',
          'Triangle',
          'Rectangle',
          'Circle',
          'Image',
          'BackgroundImage',
          'Polygon',
          'ImageCrop',

          // Drawing options
          'ColorHtml5',
          'Color',
          'ShapeBorder',
          'BrushSize',
          'OpacityOption',

          'LineWidth',
          'StrokeWidth',

          'Resize',
          'ShapeContextMenu',
          'CloseButton',
          'OvercanvasPopup',
          'OpenPopupButton',
          'MinimizeButton',
          'ToggleVisibilityButton',
          'MovableFloatingMode',
          'FullscreenModeButton',
          'Zoom',

          'TextLineHeight',
          'TextAlign',

          'TextFontFamily',
          'TextFontSize',
          'TextFontWeight',
          'TextFontStyle',
          'TextDecoration',
          'TextColor',
          'TextBackgroundColor'
      ];

      // drawer is created as global property solely for debug purposes.
      // doing  in production is considered as bad practice
      window.drawer = new DrawerJs.Drawer(null, {
          plugins: drawerPlugins,
          pluginsConfig: {
				Image: {
					scaleDownLargeImage: true,
					maxImageSizeKb: 10240, //1MB
					cropIsActive: true
				},
				BackgroundImage: {
				    scaleDownLargeImage: true,
					maxImageSizeKb: 10240, //1MB
					//fixedBackgroundUrl: '/examples/redactor/images/vanGogh.jpg',
					imagePosition: 'center',  // one of  'center', 'stretch', 'top-left', 'top-right', 'bottom-left', 'bottom-right'
		        	acceptedMIMETypes: ['image/jpeg', 'image/png', 'image/gif'] ,
                  dynamicRepositionImage: true,
                  dynamicRepositionImageThrottle: 100,
					cropIsActive: false
				},
              Text: {
                  editIconMode : false,
                  editIconSize : 'large',
                  defaultValues : {
                    fontSize: 72,
                    lineHeight: 2,
                    textFontWeight: 'bold'
                  },
                  predefined: {
                    fontSize: [8, 12, 14, 16, 32, 40, 72],
                    lineHeight: [1, 2, 3, 4, 6]
                  }
              },
				Zoom: {
					enabled: true, 
					showZoomTooltip: true, 
					useWheelEvents: true,
					zoomStep: 1.05, 
					defaultZoom: 1, 
					maxZoom: 32,
					minZoom: 1, 
					smoothnessOfWheel: 0,
					//Moving:
					enableMove: true,
					enableWhenNoActiveTool: true,
					enableButton: true
				}
			},
          toolbars: {
              drawingTools: {
					position: 'top',         
					positionType: 'outside',
					customAnchorSelector: '#custom-toolbar-here',  
					compactType: 'scrollable',   
					hidden: false,     
					toggleVisibilityButton: false,
					fullscreenMode: {
						position: 'top', 
						hidden: false,
						toggleVisibilityButton: false
					}
				},
				toolOptions: {
					position: 'bottom', 
					positionType: 'inside',
					compactType: 'popup',
					hidden: false,
					toggleVisibilityButton: false,
					fullscreenMode: {
						position: 'bottom', 
						compactType: 'popup',
						hidden: false,
						toggleVisibilityButton: false
					}
				},
				settings : {
					position : 'right', 
					positionType: 'inside',					
					compactType : 'scrollable',
					hidden: false,	
					toggleVisibilityButton: false,
					fullscreenMode: {
					    position : 'right', 
						hidden: false,
						toggleVisibilityButton: false
					},
				}
          },
          defaultImageUrl: '${ctxStatic}/modules/DrawerJs-master/examples/redactor/images/drawer.jpg',
          defaultActivePlugin : { name : 'Pencil', mode : 'lastUsed'},
          debug: false,
          texts: localization_cn,
			activeColor: '#a1be13',
			transparentBackground: true,
          align: 'center',  //one of 'left', 'right', 'center', 'inline', 'floating'
			lineAngleTooltip: { enabled: true, color: 'blue',  fontSize: 15}
      }, 1200, 400);

      $('#canvas-editor').append(window.drawer.getHtml());
      window.drawer.onInsert();
      
      
  });
  </script>