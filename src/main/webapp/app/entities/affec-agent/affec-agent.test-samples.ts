import { IAffecAgent, NewAffecAgent } from './affec-agent.model';

export const sampleWithRequiredData: IAffecAgent = {
  id: 'c4dba969-461d-4808-8bec-61de282e921d',
};

export const sampleWithPartialData: IAffecAgent = {
  id: '139d3784-eb78-4a96-8f73-f6807f95768b',
  deccent: 62214,
  decoper: 'Interface',
  cdsocie: 'Port Bedfordshire B2C',
  decexer: 42686,
  cdmois: 31638,
  matric: 48796,
  cdmac: 'utilize Borders Vision-oriented',
};

export const sampleWithFullData: IAffecAgent = {
  id: '704fab07-66dc-40ab-ad84-9cde75d547ff',
  deccent: 26168,
  decagenc: 26975,
  decserv: 63518,
  decoper: 'Quality Fantastic repurpose',
  decsean: 'plum interfaces',
  cdsocie: 'Shoals payment e-commerce',
  decexer: 70174,
  cdmois: 34200,
  matric: 87385,
  matric2: 93950,
  cdmac: 'Solutions',
};

export const sampleWithNewData: NewAffecAgent = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
