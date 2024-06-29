/* global bootstrap: false */
(() => {
  'use strict'
  const tooltipTriggerList = Array.from(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
  tooltipTriggerList.forEach(tooltipTriggerEl => {
    new bootstrap.Tooltip(tooltipTriggerEl)
  })

  document.addEventListener('DOMContentLoaded', function () {
    document.querySelectorAll('[data-bs-toggle="collapse"]').forEach(function (toggle) {
      toggle.addEventListener('click', function () {
        var target = document.querySelector(this.getAttribute('data-bs-target'));
        target.classList.toggle('show');
      });
    });
  });
})();