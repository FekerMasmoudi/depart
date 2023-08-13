import { IPeriode, NewPeriode } from './periode.model';

export const sampleWithRequiredData: IPeriode = {
  id: '4cc3ebb6-4034-40c3-b9b7-36fa6263b197',
};

export const sampleWithPartialData: IPeriode = {
  id: 'e335fffc-0ba1-4a84-9596-4c95a26abcb5',
  decoper: 'array Brand',
};

export const sampleWithFullData: IPeriode = {
  id: 'ded0110a-679e-4e28-82a1-7d40be3ea965',
  decoper: 'salmon back blockchains',
  denoper: 'Nuevo e-services',
  primaire: 'Peso',
  startdate: 'Bedfordshire stable Officer',
  enddate: 'Assistant invoice',
};

export const sampleWithNewData: NewPeriode = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
