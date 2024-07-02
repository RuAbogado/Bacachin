/* global bootstrap: false */
(() => {
  'use strict';

  const tooltipTriggerList = Array.from(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
  tooltipTriggerList.forEach(tooltipTriggerEl => {
    new bootstrap.Tooltip(tooltipTriggerEl);
  });

  document.addEventListener('DOMContentLoaded', function() {
    const navLinks = document.querySelectorAll('.nav-link');
    navLinks.forEach(function(link) {
      link.addEventListener('click', function(event) {
        event.preventDefault();
        navLinks.forEach(function(link) {
          link.classList.remove('active');
        });
        link.classList.add('active');
        document.querySelectorAll('#content > div').forEach(function(content) {
          content.style.display = 'none';
        });
        const targetId = link.getAttribute('data-target');
        document.getElementById(targetId).style.display = 'block';
      });
    });

    // Al cargar la página, mostrar la sección de inicio por defecto
    document.querySelector('.nav-link[data-target="inicio"]').click();
  });

  document.addEventListener('click', function(event) {
    // Cerrar menús desplegables al hacer clic fuera
    if (!event.target.closest('.dropdown-toggle') && !event.target.closest('.dropdown-menu')) {
      document.querySelectorAll('.dropdown-menu.show').forEach(function(menu) {
        menu.classList.remove('show');
      });
    }
  });

  document.querySelectorAll('.dropdown-toggle').forEach(function(dropdown) {
    dropdown.addEventListener('click', function(event) {
      event.preventDefault();
      var menu = this.nextElementSibling;
      if (menu.classList.contains('show')) {
        menu.classList.remove('show');
      } else {
        document.querySelectorAll('.dropdown-menu').forEach(function(menu) {
          menu.classList.remove('show');
        });
        menu.classList.add('show');
      }
    });
  });
})();
