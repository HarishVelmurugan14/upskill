// ───────────────────────────────────────────────────────────────────────
//  chibi-mascot.js — random anime character mascot, bottom-right.
//  Images live in viz/chibi/ and are catalogued in viz/chibi/manifest.json.
// ───────────────────────────────────────────────────────────────────────
(function () {
  'use strict';

  const BASE = 'Scaler/DSA/Advanced/Revise/viz/chibi';
  const FRANCHISE = {
    naruto:'Naruto', sasuke:'Naruto', kakashi:'Naruto', itachi:'Naruto',
    goku:'Dragon Ball', vegeta:'Dragon Ball',
    luffy:'One Piece', zoro:'One Piece', sanji:'One Piece',
    mikey:'Tokyo Revengers', draken:'Tokyo Revengers',
    eren:'Attack on Titan', mikasa:'Attack on Titan', levi:'Attack on Titan',
    tanjiro:'Demon Slayer', nezuko:'Demon Slayer', zenitsu:'Demon Slayer', inosuke:'Demon Slayer',
    killua:'Hunter x Hunter', gon:'Hunter x Hunter'
  };

  function mount(list) {
    if (!list || !list.length) return;
    const pick = list[Math.floor(Math.random() * list.length)];
    const file = pick.file || pick;
    const nick = (file.split('/').pop() || '').replace(/\.[^.]+$/, '');
    const name = pick.name || (nick.charAt(0).toUpperCase() + nick.slice(1));
    const franchise = FRANCHISE[nick.toLowerCase()] || '';

    const div = document.createElement('div');
    div.className = 'chibi-mascot';
    div.title = franchise ? `${name} — ${franchise}` : name;
    if (franchise) div.dataset.franchise = franchise;

    const img = document.createElement('img');
    img.src = `Scaler/DSA/Advanced/Revise/${file.startsWith('viz/') ? file : 'viz/' + file}`;
    img.alt = name;
    img.loading = 'lazy';
    div.appendChild(img);

    document.body.appendChild(div);
  }

  function init() {
    fetch(`${BASE}/manifest.json`, { cache: 'no-cache' })
      .then(r => r.ok ? r.json() : Promise.reject(r.status))
      .then(mount)
      .catch(err => console.warn('chibi mascot: manifest unavailable', err));
  }

  if (document.readyState === 'loading') {
    document.addEventListener('DOMContentLoaded', init);
  } else {
    init();
  }
})();
