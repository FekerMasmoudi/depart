import { IGroupe, NewGroupe } from './groupe.model';

export const sampleWithRequiredData: IGroupe = {
  id: 'd2887529-dea7-4c4e-bc0b-4b0592159792',
  deccent: 5299,
  decagenc: 17626,
  codgrp: 53945,
};

export const sampleWithPartialData: IGroupe = {
  id: 'f70e4262-2e14-44c3-9844-c189890489a9',
  deccent: 85567,
  decagenc: 11290,
  codgrp: 60919,
  libgrpfr: 'Facilitator cross-platform up',
};

export const sampleWithFullData: IGroupe = {
  id: '25569276-b148-48cd-a108-f680d2be376c',
  deccent: 78654,
  decagenc: 74684,
  codgrp: 5238,
  libgrp: 'Auto Mayotte',
  dectyli: 'Outdoors',
  libgrpfr: 'Operations convergence',
};

export const sampleWithNewData: NewGroupe = {
  deccent: 70166,
  decagenc: 47566,
  codgrp: 2657,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
