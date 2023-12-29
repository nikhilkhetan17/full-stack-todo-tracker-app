import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-filter',
  templateUrl: './filter.component.html',
  styleUrls: ['./filter.component.css']
})
export class FilterComponent {
  filterByCategory: string = "All"

  @Output()
  filteredCategory: EventEmitter<string> = new EventEmitter<string>();

  onfilter() {
    if(!this.filterByCategory) {
      this.filterByCategory = "All";
    } else {
      this.filteredCategory.emit(this.filterByCategory)
    }
  }
}
