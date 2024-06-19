import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-footer-section',
  standalone: true,
  imports: [],
  templateUrl: './footer-section.component.html',
  styleUrl: './footer-section.component.css',
})
export class FooterSectionComponent implements OnInit {
  ngOnInit(): void {
    window.scrollTo(0, 0);
  }
}
