// ───────────────────────────────────────────────────────────────────────
//  viz/engine.js — shared rendering helpers for all viz kinds
// ───────────────────────────────────────────────────────────────────────
(function (global) {
  'use strict';

  function el(tag, cls, txt) {
    const e = document.createElement(tag);
    if (cls) e.className = cls;
    if (txt !== undefined && txt !== null) e.textContent = String(txt);
    return e;
  }

  // A single value cell with a class name (c-input, c-mid, c-active, etc.)
  function cell(val, cls) {
    return el('div', 'cell ' + (cls || 'c-input'), val);
  }

  // A horizontal row of cells representing an array, with optional per-index class map
  // A     – array of values
  // marks – { idx: 'c-mid' | 'c-active' | 'c-low' | 'c-high' | ... } (optional)
  // base  – default class for non-marked cells (default 'c-input')
  function arrayRow(A, marks, base) {
    marks = marks || {};
    base = base || 'c-input';
    const row = el('div', 'ds-row');
    A.forEach((v, i) => row.appendChild(cell(v, marks[i] || base)));
    return row;
  }

  // Index numbers above an array row
  function indexRow(n) {
    const row = el('div', 'idx-row');
    for (let i = 0; i < n; i++) row.appendChild(el('div', 'idx-num', i));
    return row;
  }

  // Pointer markers (low/mid/high/ans) below the array
  // ptrs – { low: i, mid: i, high: i, ans: i }
  function pointerRow(n, ptrs) {
    const row = el('div', 'ds-row');
    for (let i = 0; i < n; i++) {
      const cell = el('div', 'ptr-marker');
      const tags = [];
      if (ptrs.low  === i) tags.push('<span class="ptr-low">L</span>');
      if (ptrs.mid  === i) tags.push('<span class="ptr-mid">M</span>');
      if (ptrs.high === i) tags.push('<span class="ptr-high">H</span>');
      if (ptrs.ans  === i) tags.push('<span class="ptr-ans">★</span>');
      cell.innerHTML = tags.join(' ');
      row.appendChild(cell);
    }
    return row;
  }

  // Labelled block (label text + a row)
  function block(labelTxt, contentEl) {
    const b = el('div', 'ds-block');
    b.appendChild(el('div', 'ds-label', labelTxt));
    b.appendChild(contentEl);
    return b;
  }

  // Reset the canvas and dump a fresh tree
  function paint(canvas, children) {
    canvas.innerHTML = '';
    children.forEach(c => canvas.appendChild(c));
  }

  // A number-line visualization for BS-on-answer
  // range = {min, max}, mid, low, high, ans (all numbers); feasible (bool)
  function numberLine(range, low, high, mid, ans) {
    const wrap = el('div', 'num-line');
    wrap.appendChild(el('div', 'num-line-track'));

    function pct(v) {
      const span = range.max - range.min;
      if (span <= 0) return 0;
      return ((v - range.min) / span) * 100;
    }

    if (low !== undefined && high !== undefined && high >= low) {
      const act = el('div', 'num-line-active');
      act.style.left = pct(low) + '%';
      act.style.width = (pct(high) - pct(low)) + '%';
      wrap.appendChild(act);
    }

    function place(cls, label, v) {
      if (v === undefined || v === null) return;
      const m = el('div', 'num-marker ' + cls, label + '=' + v);
      m.style.left = pct(v) + '%';
      wrap.appendChild(m);
    }
    place('low',  'lo',  low);
    place('high', 'hi',  high);
    place('mid',  'mid', mid);
    place('ans',  'ans', ans);

    return wrap;
  }

  // Linked-list node rendering
  function llNode(val, cls) {
    const c = el('div', 'cell ' + (cls || 'c-input'), val);
    c.style.minWidth = '46px';
    return c;
  }

  function llArrow(reversed) {
    const a = el('div');
    a.style.cssText = 'display:inline-flex;align-items:center;color:var(--text-muted);font-size:18px;padding:0 4px;';
    a.textContent = reversed ? '←' : '→';
    return a;
  }

  function llRow(values, marks, opts) {
    marks = marks || {};
    opts = opts || {};
    const row = el('div', 'ds-row');
    values.forEach((v, i) => {
      if (v === null) {
        row.appendChild(el('div', 'cell c-eliminated', 'Ø'));
      } else {
        row.appendChild(llNode(v, marks[i]));
      }
      if (i < values.length - 1) row.appendChild(llArrow(opts.reversedAfter && opts.reversedAfter[i]));
    });
    if (opts.tailNull) {
      row.appendChild(llArrow(false));
      row.appendChild(el('div', 'cell c-eliminated', '∅'));
    }
    return row;
  }

  // expose
  global.VizEngine = {
    el, cell, arrayRow, indexRow, pointerRow, block, paint,
    numberLine, llNode, llArrow, llRow,
  };

})(window);
