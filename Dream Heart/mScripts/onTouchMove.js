#pragma strict

var imageDestinationFlag :Texture2D;  
  
function OnGUI () {  
    
     var touchCount = AndroidInput.touchCountSecondary;  
     for(var i = 0; i < touchCount; i++)  
     {  
        var iPos = AndroidInput.GetSecondaryTouch(i).position;  
        var x = iPos.x;  
        var y = iPos.y;  
          
        GUI.DrawTexture(Rect(x,960 - y ,120,120),imageDestinationFlag);  
          
        GUI.Label(Rect(x, 960 - y,120,120),"Touch position is  " + iPos);  
     }  
  
  
}  