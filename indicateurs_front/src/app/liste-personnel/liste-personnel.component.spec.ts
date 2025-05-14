import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ListePersonnelComponent } from './liste-personnel.component';

describe('ListePersonnelComponent', () => {
  let component: ListePersonnelComponent;
  let fixture: ComponentFixture<ListePersonnelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, ListePersonnelComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListePersonnelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
