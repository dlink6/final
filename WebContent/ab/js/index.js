// Produces width of .barChart
$(document).ready(function() {
  $('.graph-bar').each(function() {
     var dataWidth = $(this).data('value');
     $(this).css("width", dataWidth + "%");
  });
});

// Positioning of .bubbleChart
$(document).ready(function() {
  $('.chart-bubble').each(function() {
    // Bubble Size
    var bubbleSize = $(this).data('value');    
    $(this).css("width", function() {
      return (bubbleSize * 10) + "px"
    });
    $(this).css("height", function() {
      return (bubbleSize * 10) + "px"
    });
    
    // Bubble Position
    var posX = $(this).data('x');
    var posY = $(this).data('y');    
    $(this).css("left", function() {
      return posX - (bubbleSize * 0.5) + "%"
    });
    $(this).css("bottom", function() {
      return posY - (bubbleSize * 0.5) + "%"
    });
  }); 
});