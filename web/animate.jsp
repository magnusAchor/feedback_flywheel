<!DOCTYPE html>
<svg viewBox="0 0 400 400" xmlns="http://www.w3.org/2000/svg">

  <rect x="0" y="0" width="400" height="400" fill="#f0f0f0" />

  <g id="man">
    <circle cx="200" cy="200" r="50" fill="#f0e0c0" /> <line x1="200" y1="150" x2="200" y2="250" stroke="#808080" stroke-width="5" /> <line x1="180" y1="250" x2="220" y2="250" stroke="#808080" stroke-width="5" /> <circle cx="180" cy="180" r="5" fill="#000000" /> <circle cx="220" cy="180" r="5" fill="#000000" /> <line x1="190" y1="190" x2="210" y2="190" stroke="#000000" stroke-width="2" /> </g>

  <path id="umbrella" fill="#ffcc00" d="M150 50 L250 50 A100 100 0 0 0 250 150 L150 150 Z" transform="translate(100, 100) rotate(-180)" />

  <g id="suggestion">
    <rect x="120" y="200" width="160" height="80" rx="10" ry="10" fill="#fff" stroke="#000" stroke-width="2" />
   <text id="suggestionText" x="138" y="220" font-size="12" font-family="Arial" class="text-color"></text>
<text id="suggestionText1" x="138" y="235" font-size="12" font-family="Arial" class="text-color"></text>
<text id="suggestionText2" x="138" y="250" font-size="12" font-family="Arial" class="text-color"></text>
<text id="suggestionText3" x="138" y="265" font-size="12" font-family="Arial" class="text-color"></text>

  </g>

  <animateTransform attributeName="transform"
                   attributeType="XML"
                   dur="10s"
                   type="translate"
                   from="0 -400"
                   to="0 0"
                   repeatCount="indefinite"
                   fill="freeze">
     <use xlink:href="#animation" /> </animateTransform>

   <animateTransform id="animation" attributeName="transform"
                    attributeType="XML"
                    dur="10s"
                    type="translate"
                    from="0 0"
                    repeatCount="indefinite"
                    to="0 -400"
                    begin="2s"  fill="freeze" />

  <script>
    const textElement = document.getElementById("suggestionText");
    const text = "Your suggestion  ";
    let displayedText = "";

    function typeWriter() {
      if (displayedText.length < text.length) {
        displayedText += text.charAt(displayedText.length);
        textElement.textContent = displayedText;
        setTimeout(typeWriter, 50); // Adjust speed here (lower for faster typing)
      }
    }

const textElement1 = document.getElementById("suggestionText1");
    const text1 = "doesn't really matter ";
    let displayedText1 = "";

    function typeWriter1() {
      if (displayedText1.length < text1.length) {
        displayedText1 += text1.charAt(displayedText1.length);
        textElement1.textContent = displayedText1;
        setTimeout(typeWriter1, 50); // Adjust speed here (lower for faster typing)
      }
    }
    
    const textElement2 = document.getElementById("suggestionText2");
    const text2 = "but well go ahead, ";
    let displayedText2 = "";

    function typeWriter2() {
      if (displayedText2.length < text2.length) {
        displayedText2 += text2.charAt(displayedText2.length);
        textElement2.textContent = displayedText2;
        setTimeout(typeWriter2, 50); // Adjust speed here (lower for faster typing)
      }
    }
    
    const textElement3 = document.getElementById("suggestionText3");
    const text3 = "capping is free";
    let displayedText3 = "";

    function typeWriter3() {
      if (displayedText3.length < text3.length) {
        displayedText3 += text3.charAt(displayedText3.length);
        textElement3.textContent = displayedText3;
        setTimeout(typeWriter3, 50); // Adjust speed here (lower for faster typing)
      }
    }
    typeWriter();
     typeWriter1();
      typeWriter2();
       typeWriter3();
           function repeatAnimation() {
      displayedText = "";
      displayedText1 = "";
      displayedText2 = "";
      displayedText3 = "";
      
      textElement.textContent = "";
      textElement1.textContent = "";
      textElement2.textContent = "";
      textElement3.textContent = "";
      
      setTimeout(typeWriter, 0);
      setTimeout(typeWriter1, 50);
      setTimeout(typeWriter2, 100);
      setTimeout(typeWriter3, 150);
      
      setTimeout(repeatAnimation, 5000); // Repeat after 5 seconds
    }

    repeatAnimation(); // Start the repeating animation

  </script>

</svg>

<style>
  svg {
    position: fixed;
    z-index: -4;
  }
  .text-color {
    fill: #000000;  /* Change this to your desired color */
  }
</style>
