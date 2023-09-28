import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { DepartComponent } from './list/depart.component';
import { DepartDetailComponent } from './detail/depart-detail.component';
import { DepartUpdateComponent } from './update/depart-update.component';
import { DepartDeleteDialogComponent } from './delete/depart-delete-dialog.component';
import { DepartRoutingModule } from './route/depart-routing.module';
import { ListMotifaComponent } from './list-motifa/list-motifa.component';
import { MatButtonModule } from '@angular/material/button';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatDialogModule } from '@angular/material/dialog';
import { MatInputModule } from '@angular/material/input';
import { CommonModule } from '@angular/common';
import { MatSelectModule } from '@angular/material/select';
import { MatIconModule } from '@angular/material/icon';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { ListMotifaModule } from './list-motifa/list-motifa.module';

const MaterialComponents = [
  MatButtonModule,
  MatButtonToggleModule,
  MatFormFieldModule,
  MatDialogModule,
  MatInputModule,
  FormsModule,
  CommonModule,
  MatSelectModule,
  MatIconModule,
];

@NgModule({
  imports: [SharedModule, DepartRoutingModule, ListMotifaComponent, ListMotifaModule, MaterialComponents, FormsModule, CommonModule],
  declarations: [DepartComponent, DepartDetailComponent, DepartUpdateComponent, DepartDeleteDialogComponent],

  exports: [MaterialComponents],
})
export class DepartModule {}
