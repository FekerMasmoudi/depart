import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { MatDialogModule, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { CommonModule } from '@angular/common';
import { IDepart } from '../depart.model';
import { IMotifa } from 'app/entities/motifa/motifa.model';
import { BrowserModule } from '@angular/platform-browser';
import { DepartComponent } from '../list/depart.component';

@Component({
  selector: 'jhi-list-motifa',
  templateUrl: './list-motifa.component.html',
  styleUrls: ['./list-motifa.component.scss'],
  standalone: true,
  imports: [MatDialogModule, MatFormFieldModule, MatInputModule, FormsModule, MatButtonModule, CommonModule, MatSelectModule],
})
export class ListMotifaComponent implements OnInit {
  @ViewChild(DepartComponent) x!: DepartComponent;

  constructor(public dialogRef: MatDialogRef<ListMotifaComponent>, @Inject(MAT_DIALOG_DATA) public data: IMotifa) {}

  listmotifa: IMotifa[] = [];
  ngOnInit(): void {
    console.log(this.data.libmotif);
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  onChange(event: IMotifa): void {
    alert(event.libmotif);
    //console.log(event.libmotif);
  }
}
