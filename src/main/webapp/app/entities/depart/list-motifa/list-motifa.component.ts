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
import { ActivatedRoute, Data, ParamMap } from '@angular/router';

import { EntityArrayResponseType } from '../service/depart.service';
import { HttpHeaders } from '@angular/common/http';
import { ITEMS_PER_PAGE, PAGE_HEADER, TOTAL_COUNT_RESPONSE_HEADER } from 'app/config/pagination.constants';
import { ASC, DEFAULT_SORT_DATA, DESC, SORT } from 'app/config/navigation.constants';
import { MotifaService } from 'app/entities/motifa/service/motifa.service';

@Component({
  selector: 'jhi-list-motifa',
  templateUrl: './list-motifa.component.html',
  styleUrls: ['./list-motifa.component.scss'],
  standalone: true,
  imports: [MatDialogModule, MatFormFieldModule, MatInputModule, FormsModule, MatButtonModule, CommonModule, MatSelectModule],
})
export class ListMotifaComponent implements OnInit {
  constructor(
    public dialogRef: MatDialogRef<ListMotifaComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    protected activatedRoute: ActivatedRoute,
    protected motifaService: MotifaService
  ) {}

  listmotifa: IMotifa[] = [];
  motifas!: IMotifa[] | null;
  //motifas1: IMotifa[]=[{"id":"0","decmotif":4,"libmotif":"ee"}];

  ngOnInit(): void {
    //console.log(this.listmotifa[0].libmotif);
    this.motifaService.queryTakaza().subscribe(data => {
      this.motifas = data.body;
    });
    console.log(this.motifas);
  }

  onNoClick(): void {
    this.dialogRef.close();
  }
}
