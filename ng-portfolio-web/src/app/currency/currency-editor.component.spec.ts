import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {CurrencyEditorComponent} from './currency-editor.component';

describe('CurrencyEditorComponent', () => {
  let component: CurrencyEditorComponent;
  let fixture: ComponentFixture<CurrencyEditorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [CurrencyEditorComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CurrencyEditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
