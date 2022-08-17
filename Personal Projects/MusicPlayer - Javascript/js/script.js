const musicContainer= document.querySelector('.music-conatainer')
const playBtn= document.querySelector('#play')
const prevBtn= document.querySelector('#prev')
const nextBtn= document.querySelector('#next')
const audio= document.querySelector('#audio')
const progress= document.querySelector('.progress')
const progressContainer = document.querySelector('.progress-container')
const title = document.querySelector('#title')
const cover = document.querySelector('#cover')

// song titles
const songs = ['pudf', 'Shanty', 'Dear Theodosia']

// keep track of songs
let songIndex = 1;

// Load song initially into DOM
loadSong(songs[songIndex])

// update song details
function loadSong(song){
    title.innerText = song
    audio.src = 'music/${song}.mp3'
    cover.src = 'images/${song}.jpg'
}

