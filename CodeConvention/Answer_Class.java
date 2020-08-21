class Snippet { 
function updateScore(newScore) {
  this.score = newScore;
  window.location.hash = "score=" + newScore;
}

}