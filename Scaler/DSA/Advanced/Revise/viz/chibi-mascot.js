// ───────────────────────────────────────────────────────────────────────
//  chibi-mascot.js — picks a random chibi anime face for the floating
//  bottom-right mascot. Hand-drawn SVG (simple but recognizable).
//  Franchises: Naruto, Dragon Ball, One Piece, Tokyo Revengers,
//              Attack on Titan, Demon Slayer.
// ───────────────────────────────────────────────────────────────────────
(function () {
  'use strict';

  // Each chibi: 100×100 viewBox, round head, signature hair, ^_^ smile
  // Reusable face primitives:
  //   eyes: two black arcs (closed-eye chibi smile)
  //   mouth: tiny 'w' curve
  const FACE = `
    <!-- eyes -->
    <path d="M 36 52 Q 40 48 44 52" stroke="#1a1a1a" stroke-width="2.5" fill="none" stroke-linecap="round"/>
    <path d="M 56 52 Q 60 48 64 52" stroke="#1a1a1a" stroke-width="2.5" fill="none" stroke-linecap="round"/>
    <!-- blush -->
    <circle cx="32" cy="62" r="4" fill="#ffb6c1" opacity="0.55"/>
    <circle cx="68" cy="62" r="4" fill="#ffb6c1" opacity="0.55"/>
    <!-- mouth -->
    <path d="M 46 64 Q 50 68 54 64" stroke="#1a1a1a" stroke-width="2" fill="none" stroke-linecap="round"/>
  `;

  const CHIBIS = [
    {
      name: 'Naruto',
      svg: `<svg viewBox="0 0 100 100" xmlns="http://www.w3.org/2000/svg">
        <!-- face -->
        <ellipse cx="50" cy="55" rx="26" ry="28" fill="#fde0c2"/>
        <!-- spiky blonde hair -->
        <path d="M 24 48
                 L 22 30 L 32 38 L 30 22 L 42 34 L 44 18 L 54 32
                 L 58 18 L 66 34 L 70 24 L 72 38 L 78 32 L 76 48 Z"
              fill="#f5c542" stroke="#b8881d" stroke-width="1.5"/>
        <!-- headband -->
        <rect x="22" y="40" width="56" height="8" fill="#1f4e8c" stroke="#0e2a52" stroke-width="1.2"/>
        <circle cx="50" cy="44" r="3" fill="#c0c8d0" stroke="#5a626a" stroke-width="0.8"/>
        <!-- whisker marks -->
        <line x1="28" y1="58" x2="36" y2="58" stroke="#7d4a1f" stroke-width="1.2"/>
        <line x1="28" y1="62" x2="36" y2="62" stroke="#7d4a1f" stroke-width="1.2"/>
        <line x1="64" y1="58" x2="72" y2="58" stroke="#7d4a1f" stroke-width="1.2"/>
        <line x1="64" y1="62" x2="72" y2="62" stroke="#7d4a1f" stroke-width="1.2"/>
        ${FACE}
      </svg>`
    },
    {
      name: 'Goku',
      svg: `<svg viewBox="0 0 100 100" xmlns="http://www.w3.org/2000/svg">
        <ellipse cx="50" cy="58" rx="26" ry="28" fill="#fde0c2"/>
        <!-- black spiky hair (Goku-style 5-spike) -->
        <path d="M 24 50
                 L 18 28 L 30 36 L 28 18 L 40 30 L 42 14 L 52 28
                 L 62 14 L 60 30 L 72 18 L 70 36 L 82 28 L 76 50 Z"
              fill="#1a1a1a" stroke="#000" stroke-width="1.2"/>
        ${FACE}
      </svg>`
    },
    {
      name: 'Luffy',
      svg: `<svg viewBox="0 0 100 100" xmlns="http://www.w3.org/2000/svg">
        <ellipse cx="50" cy="60" rx="26" ry="26" fill="#fde0c2"/>
        <!-- short black hair under hat -->
        <path d="M 26 56 Q 30 46 40 44 L 60 44 Q 70 46 74 56 L 70 50 L 60 48 L 50 48 L 40 48 L 30 50 Z" fill="#1a1a1a"/>
        <!-- straw hat brim -->
        <ellipse cx="50" cy="40" rx="38" ry="6" fill="#f0c674" stroke="#9c7a2a" stroke-width="1.5"/>
        <!-- hat crown -->
        <ellipse cx="50" cy="32" rx="20" ry="10" fill="#f5d188" stroke="#9c7a2a" stroke-width="1.5"/>
        <!-- red band -->
        <ellipse cx="50" cy="36" rx="20" ry="2.5" fill="#c0392b"/>
        <!-- scar under left eye -->
        <path d="M 36 56 L 38 60" stroke="#7d4a1f" stroke-width="1.5" stroke-linecap="round"/>
        <path d="M 34 58 L 40 58" stroke="#7d4a1f" stroke-width="1.5" stroke-linecap="round"/>
        ${FACE}
      </svg>`
    },
    {
      name: 'Mikey',
      svg: `<svg viewBox="0 0 100 100" xmlns="http://www.w3.org/2000/svg">
        <ellipse cx="50" cy="58" rx="26" ry="28" fill="#fde0c2"/>
        <!-- blonde undercut, long top -->
        <path d="M 24 56
                 L 22 32 Q 26 18 50 16 Q 74 18 78 32 L 76 56
                 L 70 44 L 60 42 L 50 44 L 40 42 L 30 44 Z"
              fill="#f5d480" stroke="#a98a2c" stroke-width="1.2"/>
        <!-- side bangs -->
        <path d="M 24 54 L 30 60 L 34 50" fill="#f5d480" stroke="#a98a2c" stroke-width="1.2"/>
        <path d="M 76 54 L 70 60 L 66 50" fill="#f5d480" stroke="#a98a2c" stroke-width="1.2"/>
        ${FACE}
      </svg>`
    },
    {
      name: 'Eren',
      svg: `<svg viewBox="0 0 100 100" xmlns="http://www.w3.org/2000/svg">
        <ellipse cx="50" cy="58" rx="26" ry="28" fill="#fde0c2"/>
        <!-- brown messy hair -->
        <path d="M 24 56
                 L 22 32 Q 30 18 50 18 Q 70 18 78 32 L 76 56
                 L 70 46 L 64 50 L 58 46 L 52 50 L 46 46 L 40 50 L 34 46 L 30 50 Z"
              fill="#6b4222" stroke="#3a230f" stroke-width="1.2"/>
        <!-- side bangs covering forehead -->
        <path d="M 28 38 L 36 52 L 44 38 L 50 50 L 56 38 L 64 52 L 72 38" stroke="#3a230f" stroke-width="1.5" fill="none"/>
        <!-- collar hint (green corps cape edge) -->
        <path d="M 24 86 Q 50 78 76 86 L 76 92 L 24 92 Z" fill="#5a7a3e" stroke="#2f4520" stroke-width="1.2"/>
        ${FACE}
      </svg>`
    },
    {
      name: 'Tanjiro',
      svg: `<svg viewBox="0 0 100 100" xmlns="http://www.w3.org/2000/svg">
        <ellipse cx="50" cy="58" rx="26" ry="28" fill="#fde0c2"/>
        <!-- dark red hair -->
        <path d="M 24 56
                 L 22 30 Q 28 16 50 14 Q 72 16 78 30 L 76 56
                 L 70 44 L 62 46 L 54 44 L 50 48 L 46 44 L 38 46 L 30 44 Z"
              fill="#5a1f1f" stroke="#2f0d0d" stroke-width="1.2"/>
        <!-- scar on forehead -->
        <path d="M 56 42 L 60 46 L 58 50" stroke="#a73a2a" stroke-width="1.8" fill="none" stroke-linecap="round"/>
        <!-- hanafuda earring (yellow square) -->
        <rect x="20" y="60" width="6" height="6" fill="#f5d188" stroke="#9c7a2a" stroke-width="0.8"/>
        <line x1="23" y1="60" x2="23" y2="58" stroke="#9c7a2a" stroke-width="0.8"/>
        <!-- checkered haori collar hint -->
        <path d="M 24 86 Q 50 78 76 86 L 76 92 L 24 92 Z" fill="#1a1a1a"/>
        <rect x="28" y="84" width="6" height="4" fill="#5a7a3e"/>
        <rect x="40" y="86" width="6" height="4" fill="#5a7a3e"/>
        <rect x="54" y="86" width="6" height="4" fill="#5a7a3e"/>
        <rect x="66" y="84" width="6" height="4" fill="#5a7a3e"/>
        ${FACE}
      </svg>`
    },
  ];

  function mount() {
    const pick = CHIBIS[Math.floor(Math.random() * CHIBIS.length)];
    const node = document.createElement('div');
    node.className = 'chibi-mascot';
    node.setAttribute('data-name', pick.name);
    node.setAttribute('title', pick.name);
    node.innerHTML = pick.svg;
    document.body.appendChild(node);
  }

  if (document.readyState === 'loading') {
    document.addEventListener('DOMContentLoaded', mount);
  } else {
    mount();
  }
})();
