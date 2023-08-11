import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILigne } from '../ligne.model';
import { DataUtils } from 'app/core/util/data-util.service';

@Component({
  selector: 'jhi-ligne-detail',
  templateUrl: './ligne-detail.component.html',
})
export class LigneDetailComponent implements OnInit {
  ligne: ILigne | null = null;

  constructor(protected dataUtils: DataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ligne }) => {
      this.ligne = ligne;
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(base64String: string, contentType: string | null | undefined): void {
    this.dataUtils.openFile(base64String, contentType);
  }

  previousState(): void {
    window.history.back();
  }
}
