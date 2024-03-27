const phraseElement = document.getElementById('phrase');
const phraseText = phraseElement.textContent;
let currentIndex = 0;

function animatePhrase() {
  if (currentIndex < phraseText.length) {
    phraseElement.textContent = phraseText.slice(0, currentIndex + 1);
    currentIndex++;
    setTimeout(animatePhrase, 100);
  }
}

animatePhrase();
