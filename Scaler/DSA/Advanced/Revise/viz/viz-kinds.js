// ───────────────────────────────────────────────────────────────────────
//  viz/viz-kinds.js — 14 visualizer kinds
//  Each kind exposes: makeSteps(data) → [{...step, msg}], render(canvas, step, data)
//  Optional: footer(data) → string
// ───────────────────────────────────────────────────────────────────────
(function (global) {
  'use strict';
  const E = global.VizEngine;

  // ╭─────────────────────────────────────────────────────────────────╮
  //  Helpers shared across BS kinds
  // ╰─────────────────────────────────────────────────────────────────╯

  // Standard BS step builder. Returns list of states {low, high, mid, decision, ans?}
  function bsSteps(A, B, opts) {
    opts = opts || {}; // mode: 'first' | 'last' | 'eq' | 'lower' | 'upper'
    const mode = opts.mode || 'eq';
    const steps = [];
    let low = 0, high = A.length - 1, ans = -1;

    steps.push({ low, high, mid: null, msg: `Init: low=0, high=${A.length - 1}, looking for ${B}` });

    while (low <= high) {
      const mid = low + ((high - low) >> 1);
      const v = A[mid];
      let dec, msg;
      if (mode === 'first') {
        if (v < B)      { dec = 'right'; msg = `A[${mid}]=${v} < ${B} → go right`; }
        else if (v > B) { dec = 'left';  msg = `A[${mid}]=${v} > ${B} → go left`; }
        else            { dec = 'hit-left'; ans = mid; msg = `A[${mid}]=${v} == ${B} → record, keep looking LEFT for earlier`; }
      } else if (mode === 'last') {
        if (v < B)      { dec = 'right'; msg = `A[${mid}]=${v} < ${B} → go right`; }
        else if (v > B) { dec = 'left';  msg = `A[${mid}]=${v} > ${B} → go left`; }
        else            { dec = 'hit-right'; ans = mid; msg = `A[${mid}]=${v} == ${B} → record, keep looking RIGHT for later`; }
      } else if (mode === 'lower') {
        if (v < B)      { dec = 'right'; msg = `A[${mid}]=${v} < ${B} → go right`; }
        else            { dec = 'left-ans'; ans = mid; msg = `A[${mid}]=${v} ≥ ${B} → candidate, try left for earlier`; }
      } else {
        if (v < B)      { dec = 'right'; msg = `A[${mid}]=${v} < ${B} → go right`; }
        else if (v > B) { dec = 'left';  msg = `A[${mid}]=${v} > ${B} → go left`; }
        else            { ans = mid; steps.push({ low, high, mid, ans, msg: `A[${mid}]=${v} == ${B} → FOUND ✓`, done: true }); return steps; }
      }
      steps.push({ low, high, mid, ans, msg });
      if (dec === 'right')      low = mid + 1;
      else if (dec === 'left')  high = mid - 1;
      else if (dec === 'left-ans' || dec === 'hit-left') high = mid - 1;
      else if (dec === 'hit-right') low = mid + 1;
    }
    steps.push({ low, high, mid: null, ans, msg: ans === -1 ? `Loop ends, target not found` : `Loop ends, answer = ${ans}` });
    return steps;
  }

  function bsArrayRender(canvas, step, A) {
    const marks = {};
    for (let i = 0; i < A.length; i++) {
      if (step.low !== undefined && (i < step.low || i > step.high)) marks[i] = 'c-eliminated';
    }
    if (step.low  !== undefined && step.low  >= 0 && step.low  < A.length) marks[step.low]  = marks[step.low]  || 'c-low';
    if (step.high !== undefined && step.high >= 0 && step.high < A.length) marks[step.high] = marks[step.high] || 'c-high';
    if (step.mid  !== undefined && step.mid !== null && step.mid >= 0 && step.mid < A.length) marks[step.mid] = 'c-mid';
    if (step.ans  !== undefined && step.ans !== null && step.ans >= 0 && step.ans < A.length && step.done) marks[step.ans] = 'c-result';

    const ptrs = {};
    if (step.low  !== undefined && step.low  >= 0 && step.low  < A.length) ptrs.low  = step.low;
    if (step.high !== undefined && step.high >= 0 && step.high < A.length) ptrs.high = step.high;
    if (step.mid  !== undefined && step.mid !== null) ptrs.mid = step.mid;
    if (step.ans  !== undefined && step.ans >= 0)     ptrs.ans = step.ans;

    E.paint(canvas, [
      E.block('Array (index)', E.indexRow(A.length)),
      E.block('Array (value)', E.arrayRow(A, marks)),
      E.block('Pointers',      E.pointerRow(A.length, ptrs)),
    ]);
  }

  // ╭─────────────────────────────────────────────────────────────────╮
  //  1. bs-bound — find first + last occurrence
  // ╰─────────────────────────────────────────────────────────────────╯
  const BS_BOUND = {
    makeSteps(data) {
      const A = data.A, B = data.B;
      const a = bsSteps(A, B, { mode: 'first' }).map(s => ({ ...s, phase: 'first' }));
      const b = bsSteps(A, B, { mode: 'last'  }).map(s => ({ ...s, phase: 'last'  }));
      a[0].msg = '🔎 First-occurrence BS — ' + a[0].msg;
      b[0].msg = '🔎 Last-occurrence BS — ' + b[0].msg;
      return [...a, ...b];
    },
    render(canvas, step, data) { bsArrayRender(canvas, step, data.A); },
    footer: d => `Target = ${d.B}  |  Array length = ${d.A.length}`,
  };

  // ╭─────────────────────────────────────────────────────────────────╮
  //  2. bs-lower-bound
  // ╰─────────────────────────────────────────────────────────────────╯
  const BS_LOWER = {
    makeSteps(data) { return bsSteps(data.A, data.B, { mode: 'lower' }); },
    render(canvas, step, data) { bsArrayRender(canvas, step, data.A); },
    footer: d => `lower_bound of ${d.B} in array of length ${d.A.length}`,
  };

  // ╭─────────────────────────────────────────────────────────────────╮
  //  3. bs-peak
  // ╰─────────────────────────────────────────────────────────────────╯
  const BS_PEAK = {
    makeSteps(data) {
      const A = data.A;
      const steps = [];
      let low = 0, high = A.length - 1;
      steps.push({ low, high, mid: null, msg: 'Find any peak — walk uphill.' });
      while (low <= high) {
        const mid = (low + high) >> 1;
        const leftOk  = mid === 0           || A[mid] >= A[mid - 1];
        const rightOk = mid === A.length-1  || A[mid] >= A[mid + 1];
        if (leftOk && rightOk) {
          steps.push({ low, high, mid, ans: mid, done: true, msg: `A[${mid}]=${A[mid]} beats both neighbours → PEAK ✓` });
          return steps;
        }
        if (!rightOk) { steps.push({ low, high, mid, msg: `A[${mid}]=${A[mid]} < A[${mid+1}] → climbing, go RIGHT` }); low = mid + 1; }
        else          { steps.push({ low, high, mid, msg: `A[${mid}]=${A[mid]} < A[${mid-1}] → climbing, go LEFT` });  high = mid - 1; }
      }
      return steps;
    },
    render(canvas, step, data) {
      const A = data.A;
      const marks = {};
      for (let i = 0; i < A.length; i++) if (i < step.low || i > step.high) marks[i] = 'c-eliminated';
      if (step.mid !== null && step.mid !== undefined) marks[step.mid] = step.done ? 'c-peak' : 'c-mid';
      if (step.mid !== null && step.mid !== undefined && !step.done) {
        if (step.mid - 1 >= step.low) marks[step.mid - 1] = marks[step.mid - 1] || 'c-active';
        if (step.mid + 1 <= step.high) marks[step.mid + 1] = marks[step.mid + 1] || 'c-active';
      }
      const ptrs = { low: step.low, high: step.high };
      if (step.mid !== null && step.mid !== undefined) ptrs.mid = step.mid;
      E.paint(canvas, [
        E.block('Array',    E.arrayRow(A, marks)),
        E.block('Pointers', E.pointerRow(A.length, ptrs)),
      ]);
    },
  };

  // ╭─────────────────────────────────────────────────────────────────╮
  //  4. bs-single-element
  // ╰─────────────────────────────────────────────────────────────────╯
  const BS_SINGLE = {
    makeSteps(data) {
      const A = data.A, N = A.length, steps = [];
      if (N === 1)              { steps.push({ low:0, high:0, mid:0, done:true, ans:0, msg:'Only one element — it IS the loner.' }); return steps; }
      if (A[0] !== A[1])        { steps.push({ low:0, high:N-1, mid:0, done:true, ans:0, msg:'Boundary check: A[0] ≠ A[1] → loner is A[0].' }); return steps; }
      if (A[N-1] !== A[N-2])    { steps.push({ low:0, high:N-1, mid:N-1, done:true, ans:N-1, msg:`Boundary check: A[${N-1}] ≠ A[${N-2}] → loner is A[${N-1}].` }); return steps; }
      let low = 1, high = N - 2;
      steps.push({ low, high, mid: null, msg: 'BS on pairs: even index + matches right = loner is after; odd index + matches right = loner is before.' });
      while (low <= high) {
        let mid = (low + high) >> 1;
        if (A[mid] === A[mid + 1]) {
          if (mid % 2 === 0) { steps.push({ low, high, mid, msg: `A[${mid}]=A[${mid+1}], mid even → loner on the RIGHT` }); low = mid + 2; }
          else               { steps.push({ low, high, mid, msg: `A[${mid}]=A[${mid+1}], mid odd  → loner on the LEFT`  }); high = mid - 1; }
        } else if (A[mid] === A[mid - 1]) {
          mid--;
          if (mid % 2 === 0) { steps.push({ low, high, mid, msg: `A[${mid+1}]=A[${mid}], align mid→${mid}, even → loner on RIGHT` }); low = mid + 2; }
          else               { steps.push({ low, high, mid, msg: `A[${mid+1}]=A[${mid}], align mid→${mid}, odd  → loner on LEFT`  }); high = mid - 1; }
        } else {
          steps.push({ low, high, mid, done: true, ans: mid, msg: `A[${mid}]=${A[mid]} has no twin → LONER ✓` });
          return steps;
        }
      }
      return steps;
    },
    render(canvas, step, data) { bsArrayRender(canvas, step, data.A); },
  };

  // ╭─────────────────────────────────────────────────────────────────╮
  //  5. bs-matrix — flat-index BS on 2D
  // ╰─────────────────────────────────────────────────────────────────╯
  const BS_MATRIX = {
    makeSteps(data) {
      const M = data.A[0].length, total = data.A.length * M, B = data.B;
      const steps = [];
      let low = 0, high = total - 1;
      steps.push({ low, high, mid: null, r: null, c: null, msg: `Flat indexing: virtual array of length ${total}. Looking for ${B}.` });
      while (low <= high) {
        const mid = (low + high) >> 1;
        const r = Math.floor(mid / M), c = mid % M;
        const v = data.A[r][c];
        if (v === B)      { steps.push({ low, high, mid, r, c, done: true, msg: `A[${r}][${c}]=${v} == ${B} → FOUND ✓` }); return steps; }
        else if (v < B)   { steps.push({ low, high, mid, r, c, msg: `A[${r}][${c}]=${v} < ${B} → go right` }); low  = mid + 1; }
        else              { steps.push({ low, high, mid, r, c, msg: `A[${r}][${c}]=${v} > ${B} → go left`  }); high = mid - 1; }
      }
      steps.push({ low, high, mid: null, msg: 'Not found.' });
      return steps;
    },
    render(canvas, step, data) {
      const M = data.A[0].length, total = data.A.length * M;
      canvas.innerHTML = '';
      // grid
      const grid = E.el('div', 'ds-block');
      grid.appendChild(E.el('div', 'ds-label', '2D Matrix (flat index in corner)'));
      const table = E.el('div');
      table.style.display = 'grid';
      table.style.gridTemplateColumns = `repeat(${M}, 60px)`;
      table.style.gap = '6px';
      data.A.forEach((row, r) => row.forEach((v, c) => {
        const idx = r * M + c;
        let cls = 'c-input';
        if (idx < step.low || idx > step.high) cls = 'c-eliminated';
        if (step.r === r && step.c === c) cls = step.done ? 'c-result' : 'c-mid';
        const cell = E.el('div', 'cell ' + cls);
        cell.style.width = '60px';
        cell.style.height = '50px';
        cell.style.flexDirection = 'column';
        cell.innerHTML = `<div>${v}</div><div style="font-size:9px;opacity:0.55">#${idx}</div>`;
        table.appendChild(cell);
      }));
      grid.appendChild(table);
      canvas.appendChild(grid);

      // number-line
      const range = { min: 0, max: total - 1 };
      canvas.appendChild(E.block('Flat-index window', E.numberLine(range, step.low, step.high, step.mid)));
    },
    footer: d => `Searching ${d.B} in ${d.A.length}×${d.A[0].length} matrix`,
  };

  // ╭─────────────────────────────────────────────────────────────────╮
  //  6. bs-on-answer — generic
  // ╰─────────────────────────────────────────────────────────────────╯
  const BS_ON_ANSWER = {
    makeSteps(data) {
      // data: { low, high, target, label, predicate }  (target = expected answer)
      const lo0 = data.low, hi0 = data.high, target = data.target;
      const steps = [];
      let low = lo0, high = hi0, ans = null;
      steps.push({ low, high, mid: null, ans, msg: data.label || 'Binary search the answer range.' });

      // We simulate by judging mid vs target: if mid <= target → feasible (for minimize-max patterns);
      // a generic 'min ans' simulator.
      while (low <= high) {
        const mid = Math.floor((low + high) / 2);
        const feasible = mid >= target ? false : true; // mid below target is feasible for "max..." patterns, but to keep things visual we always converge to target.
        // Use a balanced approach: shrink toward target either way.
        if (mid < target)      { steps.push({ low, high, mid, ans, feasible: true,  msg: `mid=${mid} too small → predicate fails → low = mid+1` }); low  = mid + 1; }
        else if (mid > target) { steps.push({ low, high, mid, ans, feasible: false, msg: `mid=${mid} too large → shrink right → high = mid-1` }); high = mid - 1; }
        else                   { ans = mid; steps.push({ low, high, mid, ans, feasible: true, done: true, msg: `mid=${mid} = target → answer = ${mid} ✓` }); return steps; }
      }
      return steps;
    },
    render(canvas, step, data) {
      const range = { min: data.low, max: data.high };
      const wrap = E.el('div', 'ds-block');
      wrap.appendChild(E.el('div', 'ds-label', data.label || 'Search space'));
      wrap.appendChild(E.numberLine(range, step.low, step.high, step.mid, step.done ? step.ans : null));
      canvas.innerHTML = '';
      canvas.appendChild(wrap);
      if (data.predicate) {
        const f = E.el('div');
        f.style.cssText = 'text-align:center;font-family:JetBrains Mono,monospace;font-size:11px;color:var(--text-muted);margin-top:10px';
        f.textContent = 'Predicate: ' + data.predicate;
        canvas.appendChild(f);
      }
    },
    footer: d => `Search [${d.low}, ${d.high}], target = ${d.target}`,
  };

  // ╭─────────────────────────────────────────────────────────────────╮
  //  7. bs-rotated
  // ╰─────────────────────────────────────────────────────────────────╯
  const BS_ROTATED = {
    makeSteps(data) {
      const A = data.A, B = data.B, N = A.length, steps = [];

      // pivot-finding BS
      let low = 0, high = N - 1, pivot = 0;
      steps.push({ low, high, mid: null, msg: '1️⃣ Find rotation pivot — smallest element.' });
      while (low <= high) {
        const mid = (low + high) >> 1;
        if (A[mid] >= A[0]) { steps.push({ low, high, mid, msg: `A[${mid}]=${A[mid]} ≥ A[0]=${A[0]} → pivot on RIGHT` }); low = mid + 1; }
        else                { steps.push({ low, high, mid, msg: `A[${mid}]=${A[mid]} < A[0] → candidate pivot, look LEFT` }); pivot = mid; high = mid - 1; }
      }
      steps.push({ low: pivot, high: pivot, mid: pivot, done: true, ans: pivot, pivot, msg: `Pivot index = ${pivot} (value ${A[pivot]})` });

      // Choose half
      let lo, hi;
      if (B === A[0]) { steps.push({ low:0, high:0, mid:0, done:true, ans:0, msg:`${B} == A[0] → FOUND at 0` }); return steps; }
      if (B > A[0])   { lo = 0;     hi = (pivot > 0 ? pivot - 1 : N - 1); steps.push({ low: lo, high: hi, msg: `${B} > A[0]=${A[0]} → search LEFT half [${lo}, ${hi}]` }); }
      else            { lo = pivot; hi = N - 1; steps.push({ low: lo, high: hi, msg: `${B} < A[0]=${A[0]} → search RIGHT half [${lo}, ${hi}]` }); }

      while (lo <= hi) {
        const mid = (lo + hi) >> 1;
        const v = A[mid];
        if (v === B)    { steps.push({ low: lo, high: hi, mid, done: true, ans: mid, msg: `A[${mid}]=${v} == ${B} → FOUND ✓` }); return steps; }
        else if (v < B) { steps.push({ low: lo, high: hi, mid, msg: `A[${mid}]=${v} < ${B} → right` }); lo = mid + 1; }
        else            { steps.push({ low: lo, high: hi, mid, msg: `A[${mid}]=${v} > ${B} → left`  }); hi = mid - 1; }
      }
      steps.push({ low: lo, high: hi, msg: 'Not found.' });
      return steps;
    },
    render(canvas, step, data) { bsArrayRender(canvas, step, data.A); },
    footer: d => `Searching ${d.B} in rotated array of length ${d.A.length}`,
  };

  // ╭─────────────────────────────────────────────────────────────────╮
  //  8. bs-partition (median of two sorted)
  // ╰─────────────────────────────────────────────────────────────────╯
  const BS_PARTITION = {
    makeSteps(data) {
      let A = data.A, B = data.B;
      let swapped = false;
      if (A.length > B.length) { [A, B] = [B, A]; swapped = true; }
      const n = A.length, m = B.length, leftCount = ((n + m + 1) >> 1);
      const steps = [];
      let low = 0, high = n;
      steps.push({ low, high, pX: null, pY: null, A, B, msg: `Partition the SHORTER array (length ${n}). leftCount = ${leftCount}` });
      while (low <= high) {
        const pX = (low + high) >> 1;
        const pY = leftCount - pX;
        const Lx = pX === 0 ? -Infinity : A[pX - 1];
        const Rx = pX === n ?  Infinity : A[pX];
        const Ly = pY === 0 ? -Infinity : B[pY - 1];
        const Ry = pY === m ?  Infinity : B[pY];
        const ok = Lx <= Ry && Ly <= Rx;
        let msg;
        if (ok) {
          let median;
          if ((n + m) % 2 === 0) median = (Math.max(Lx, Ly) + Math.min(Rx, Ry)) / 2;
          else                   median = Math.max(Lx, Ly);
          msg = `✓ Lx=${fin(Lx)} ≤ Ry=${fin(Ry)} && Ly=${fin(Ly)} ≤ Rx=${fin(Rx)} → median = ${median}`;
          steps.push({ low, high, pX, pY, A, B, done: true, median, msg });
          return steps;
        }
        if (Lx > Ry) { msg = `✗ Lx=${fin(Lx)} > Ry=${fin(Ry)} → cut A too far right, high = pX-1`; steps.push({ low, high, pX, pY, A, B, msg }); high = pX - 1; }
        else         { msg = `✗ Ly=${fin(Ly)} > Rx=${fin(Rx)} → cut A too far left,  low  = pX+1`; steps.push({ low, high, pX, pY, A, B, msg }); low  = pX + 1; }
      }
      return steps;

      function fin(x) { return x === -Infinity ? '-∞' : x === Infinity ? '+∞' : x; }
    },
    render(canvas, step) {
      canvas.innerHTML = '';

      function partitionedRow(arr, p, label) {
        const wrap = E.el('div', 'ds-block');
        wrap.appendChild(E.el('div', 'ds-label', label));
        const row = E.el('div', 'ds-row');
        arr.forEach((v, i) => {
          const c = E.cell(v, p !== null && i < p ? 'c-active' : 'c-input');
          row.appendChild(c);
          if (p !== null && i === p - 1) {
            const sep = E.el('div'); sep.style.cssText = 'width:3px;height:36px;background:var(--purple);margin:0 2px;border-radius:2px;';
            row.appendChild(sep);
          }
        });
        if (p === arr.length) {
          const sep = E.el('div'); sep.style.cssText = 'width:3px;height:36px;background:var(--purple);margin:0 2px;border-radius:2px;';
          row.appendChild(sep);
        }
        wrap.appendChild(row);
        return wrap;
      }

      canvas.appendChild(partitionedRow(step.A, step.pX, `Array A (cut at pX = ${step.pX})`));
      canvas.appendChild(partitionedRow(step.B, step.pY, `Array B (cut at pY = ${step.pY})`));
      if (step.done) {
        const f = E.el('div');
        f.style.cssText = 'text-align:center;color:var(--green);font-size:14px;font-weight:600;margin-top:14px;';
        f.textContent = '🎯 Median = ' + step.median;
        canvas.appendChild(f);
      }
    },
    footer: () => 'BS on the partition cut — left max ≤ right min on both sides',
  };

  // ╭─────────────────────────────────────────────────────────────────╮
  //  9. sliding-window
  // ╰─────────────────────────────────────────────────────────────────╯
  const SLIDING_WINDOW = {
    makeSteps(data) {
      const A = data.A, K = data.K, B = data.B;
      const steps = [];
      if (K <= 0) { steps.push({ start: -1, end: -1, sum: 0, msg: 'K=0 → trivially OK' }); return steps; }
      if (K > A.length) { steps.push({ start: -1, end: -1, sum: 0, msg: `K=${K} > N=${A.length}` }); return steps; }
      let sum = 0;
      for (let i = 0; i < K; i++) sum += A[i];
      steps.push({ start: 0, end: K - 1, sum, msg: `Initial window [0..${K-1}] sum = ${sum}` });
      for (let i = K; i < A.length; i++) {
        sum += A[i] - A[i - K];
        steps.push({ start: i - K + 1, end: i, sum, msg: `Slide → window [${i-K+1}..${i}] sum = ${sum}` + (B !== undefined ? `  (limit ${B}: ${sum > B ? '✗' : '✓'})` : '') });
      }
      return steps;
    },
    render(canvas, step, data) {
      const A = data.A;
      const marks = {};
      for (let i = step.start; i <= step.end; i++) marks[i] = 'c-active';
      E.paint(canvas, [
        E.block('Array',  E.arrayRow(A, marks)),
        E.block('Window', E.pointerRow(A.length, { low: step.start, high: step.end })),
      ]);
      const sumLine = E.el('div');
      sumLine.style.cssText = 'text-align:center;font-family:JetBrains Mono,monospace;font-size:13px;color:var(--amber);margin-top:10px;';
      sumLine.textContent = `Σ window = ${step.sum}` + (data.B !== undefined ? `   |   limit = ${data.B}` : '');
      canvas.appendChild(sumLine);
    },
    footer: d => `K = ${d.K}` + (d.B !== undefined ? `, limit = ${d.B}` : ''),
  };

  // ╭─────────────────────────────────────────────────────────────────╮
  //  10. ll-traverse
  // ╰─────────────────────────────────────────────────────────────────╯
  const LL_TRAVERSE = {
    makeSteps(data) {
      const V = data.values;
      const steps = [{ active: -1, msg: data.label || 'Walk c = head while c != null.' }];
      for (let i = 0; i < V.length; i++) steps.push({ active: i, msg: `c = node[${i}] (value ${V[i]}) → print` });
      steps.push({ active: -1, done: true, msg: 'c == null → done.' });
      return steps;
    },
    render(canvas, step, data) {
      const marks = {};
      if (step.active >= 0) marks[step.active] = 'c-mid';
      E.paint(canvas, [
        E.block('Linked list', E.llRow(data.values, marks, { tailNull: true })),
      ]);
    },
  };

  // ╭─────────────────────────────────────────────────────────────────╮
  //  11. ll-reverse
  // ╰─────────────────────────────────────────────────────────────────╯
  const LL_REVERSE = {
    makeSteps(data) {
      const V = data.values.slice();
      const steps = [{ values: V.slice(), reversedUpTo: -1, curr: 0, msg: 'prev=null, curr=head — three-pointer dance begins.' }];
      for (let i = 0; i < V.length; i++) {
        steps.push({ values: V.slice(), reversedUpTo: i, curr: i + 1 < V.length ? i + 1 : -2, msg: i === V.length - 1 ? `Flip arrow of node[${i}]. curr == null → done.` : `Flip arrow of node[${i}]. Slide: prev=node[${i}], curr=node[${i+1}].` });
      }
      steps[steps.length - 1].done = true;
      return steps;
    },
    render(canvas, step, data) {
      const marks = {};
      const reversedAfter = [];
      for (let i = 0; i < data.values.length - 1; i++) reversedAfter.push(i <= step.reversedUpTo - 1);
      if (step.curr >= 0 && step.curr < data.values.length) marks[step.curr] = 'c-mid';
      if (step.reversedUpTo >= 0) marks[step.reversedUpTo] = 'c-result';
      E.paint(canvas, [
        E.block('Linked list (arrows flip as we go)', E.llRow(data.values, marks, { reversedAfter })),
      ]);
    },
  };

  // ╭─────────────────────────────────────────────────────────────────╮
  //  12. ll-insert
  // ╰─────────────────────────────────────────────────────────────────╯
  const LL_INSERT = {
    makeSteps(data) {
      const V = data.values.slice();
      const steps = [];
      steps.push({ values: V.slice(), pointer: -1, msg: `dummy → head. Walk to predecessor index ${data.insertAt - 1}.` });
      for (let i = 0; i < data.insertAt && i < V.length; i++) {
        steps.push({ values: V.slice(), pointer: i, msg: `prev = node[${i}] (value ${V[i]})` });
      }
      const inserted = V.slice();
      inserted.splice(data.insertAt, 0, data.insertValue);
      steps.push({ values: inserted, pointer: data.insertAt, inserted: data.insertAt, done: true, msg: `Splice: new node (${data.insertValue}) inserted at index ${data.insertAt}.` });
      return steps;
    },
    render(canvas, step) {
      const marks = {};
      if (step.pointer >= 0) marks[step.pointer] = 'c-mid';
      if (step.inserted !== undefined) marks[step.inserted] = 'c-result';
      E.paint(canvas, [
        E.block('Linked list', E.llRow(step.values, marks, { tailNull: true })),
      ]);
    },
  };

  // ╭─────────────────────────────────────────────────────────────────╮
  //  13. ll-delete
  // ╰─────────────────────────────────────────────────────────────────╯
  const LL_DELETE = {
    makeSteps(data) {
      const V = data.values.slice();
      const steps = [];
      steps.push({ values: V.slice(), pointer: -1, msg: `dummy → head. Walk to predecessor of index ${data.deleteAt}.` });
      for (let i = 0; i < data.deleteAt && i < V.length; i++) {
        steps.push({ values: V.slice(), pointer: i, msg: `prev = node[${i}]` });
      }
      steps.push({ values: V.slice(), pointer: data.deleteAt - 1, doomed: data.deleteAt, msg: `Mark node[${data.deleteAt}]=${V[data.deleteAt]} for deletion.` });
      const out = V.slice(); out.splice(data.deleteAt, 1);
      steps.push({ values: out, pointer: data.deleteAt - 1, done: true, msg: `Skip the doomed node: prev.next = prev.next.next.` });
      return steps;
    },
    render(canvas, step) {
      const marks = {};
      if (step.pointer >= 0) marks[step.pointer] = 'c-mid';
      if (step.doomed !== undefined) marks[step.doomed] = 'c-eliminated';
      E.paint(canvas, [
        E.block('Linked list', E.llRow(step.values, marks, { tailNull: true })),
      ]);
    },
  };

  // ╭─────────────────────────────────────────────────────────────────╮
  //  14. ll-two-pointer (Nth from end)
  // ╰─────────────────────────────────────────────────────────────────╯
  const LL_TWO_POINTER = {
    makeSteps(data) {
      const V = data.values, gap = data.gap;
      const steps = [];
      steps.push({ slow: -1, fast: -1, msg: `Two pointers at dummy. Advance FAST by gap=${gap} first.` });
      let f = -1;
      for (let i = 0; i < gap; i++) { f++; steps.push({ slow: -1, fast: f, msg: `fast hops → fast = ${f}` }); }
      let s = -1;
      while (f < V.length - 1) {
        f++; s++;
        steps.push({ slow: s, fast: f, msg: `Both slide. slow=${s}, fast=${f}` });
      }
      const doomed = s + 1;
      steps.push({ slow: s, fast: f, doomed, msg: `slow points to predecessor of victim (node[${doomed}]=${V[doomed]}).` });
      const out = V.slice(); out.splice(doomed, 1);
      steps.push({ slow: s, fast: f, values: out, done: true, msg: `Splice: slow.next = slow.next.next.` });
      return steps;
    },
    render(canvas, step, data) {
      const values = step.values || data.values;
      const marks = {};
      if (step.slow >= 0 && step.slow < values.length) marks[step.slow] = 'c-low';
      if (step.fast >= 0 && step.fast < values.length) marks[step.fast] = 'c-high';
      if (step.doomed !== undefined) marks[step.doomed] = 'c-eliminated';
      E.paint(canvas, [
        E.block('Linked list', E.llRow(values, marks, { tailNull: true })),
        E.block('Pointers',    E.pointerRow(values.length, { low: step.slow >= 0 ? step.slow : undefined, high: step.fast >= 0 && step.fast < values.length ? step.fast : undefined })),
      ]);
    },
  };

  // ╭─────────────────────────────────────────────────────────────────╮
  //  Export registry
  // ╰─────────────────────────────────────────────────────────────────╯
  // ╭─────────────────────────────────────────────────────────────────╮
  //  SCRIPTED_ARRAY — fully hand-authored per-problem visualizer
  //  Reads { A?, script:[{msg, marks, ptrs, label, overlay, html}], legend, footer }
  //  Each script entry is one step.
  //  - If step.html is set, it is injected as the entire canvas body
  //    (escape hatch for matrices, linked lists, custom layouts).
  //  - Else: renders an array using A + marks + ptrs.
  //  overlay is HTML drawn below the main visual.
  // ╰─────────────────────────────────────────────────────────────────╯
  const SCRIPTED_ARRAY = {
    makeSteps(data) {
      return (data.script || []).map(s => ({
        marks: s.marks || {},
        ptrs:  s.ptrs  || {},
        label: s.label || '',
        overlay: s.overlay || '',
        html:  s.html  || '',
        msg:   s.msg   || '',
      }));
    },
    render(canvas, step, data) {
      const children = [];
      if (step.label) {
        children.push(E.el('div', 'viz-step-label', step.label));
      }
      if (step.html) {
        const host = E.el('div', 'viz-custom');
        host.innerHTML = step.html;
        children.push(host);
      } else if (data.A) {
        const A = data.A;
        children.push(E.block('Array (index)', E.indexRow(A.length)));
        children.push(E.block('Array (value)', E.arrayRow(A, step.marks)));
        const hasPtrs = step.ptrs && Object.keys(step.ptrs).length > 0;
        if (hasPtrs) {
          const ptrs = {};
          ['low','mid','high','ans'].forEach(k => {
            if (step.ptrs[k] !== undefined && step.ptrs[k] !== null) ptrs[k] = step.ptrs[k];
          });
          children.push(E.block('Pointers', E.pointerRow(A.length, ptrs)));
        }
      }
      if (step.overlay) {
        const ov = E.el('div', 'viz-overlay');
        ov.innerHTML = step.overlay;
        children.push(ov);
      }
      E.paint(canvas, children);
    },
    footer: d => d.footer || '',
  };

  global.VIZ_KINDS = {
    'scripted-array':    SCRIPTED_ARRAY,
    'bs-bound':          BS_BOUND,
    'bs-lower-bound':    BS_LOWER,
    'bs-peak':           BS_PEAK,
    'bs-single-element': BS_SINGLE,
    'bs-matrix':         BS_MATRIX,
    'bs-on-answer':      BS_ON_ANSWER,
    'bs-rotated':        BS_ROTATED,
    'bs-partition':      BS_PARTITION,
    'sliding-window':    SLIDING_WINDOW,
    'll-traverse':       LL_TRAVERSE,
    'll-reverse':        LL_REVERSE,
    'll-insert':         LL_INSERT,
    'll-delete':         LL_DELETE,
    'll-two-pointer':    LL_TWO_POINTER,
  };

})(window);
