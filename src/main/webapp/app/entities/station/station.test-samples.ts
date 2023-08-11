import { IStation, NewStation } from './station.model';

export const sampleWithRequiredData: IStation = {
  id: '8a6402a8-0200-4eb5-b547-750fb48441db',
  decstat: 'Bermuda Buckinghamshire Jewelery',
};

export const sampleWithPartialData: IStation = {
  id: '8a3e221a-8ac1-4115-bf5a-5f5e50719a43',
  decstat: 'Steel',
  decrout: 'Georgia payment',
  delstat: 'calculate',
  longitude: 'Programmable Unbranded Associate',
  valide: 'Intelligent networks',
};

export const sampleWithFullData: IStation = {
  id: '7b81137c-719f-496b-aab2-e10a081a0243',
  decstat: 'Loan Account index',
  dectyst: 'matrix Corporate Fantastic',
  decrout: 'Liaison Handcrafted',
  delstat: 'Cotton',
  delstatfr: 'high-level Cross-group',
  lattitude: 'Naira Metal infomediaries',
  longitude: 'transmit',
  valide: 'multi-byte JSON',
};

export const sampleWithNewData: NewStation = {
  decstat: 'Customer schemas International',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
